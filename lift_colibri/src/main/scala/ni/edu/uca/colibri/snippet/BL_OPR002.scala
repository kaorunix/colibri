package ni.edu.uca.colibri.snippet

import ni.edu.uca.colibri._


import _root_.scala.xml.{NodeSeq, Text}
import model._
import net.liftweb._
import http._
import SHtml._
import S._
import js._
import JsCmds._
import mapper._
import util._
import Helpers._
import scala.xml.{NodeSeq, Text}
import dao._
import net.liftweb.common.Logger
import _root_.java.util.Date
import ido._
import odo._
import fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._

/**
 * BL_OPR002
 *   業務詳細画面
 *   OPR001で選択された業務の詳細を表示する。
 *   詳細は業務名称、業務説明、データ名称、データ型、データサイズ、で構成される。
 */
class BL_OPR002 {
		
	object Log extends Logger
// 削除モードの時だけ直接呼ばれるので先に設定しておく
	var mode=2
//	var ido = IDO_OPR002
	var odo= ODO_OPR002
	var lang_id:Int= 2	
	var operation_id = -1
//    mode match {
//	  case 2 => odo.body("operation_id").setValue(-1)
//      case _ => odo.body("operation_id").setValue(5)
//    }
//      operation_id = odo.body("operation_id").getValue.toString.toInt
      operation_id = Session.operation_idVar.get.toInt  
      if (Session.modeVar.get.toInt > 0) mode = Session.modeVar.get.toInt

/**
 * Initializar tabla de OPERATION by Kaoru Sato
 * 業務テーブル初期化処理
 * Initial Process for operation table
 */ 
    def init():Unit = { 
      odo.setOptions(lang_id, operation_id)
		odo.body("table") match { case t:TableType => t.elements(2).applyLang(lang_id)}
		odo.body("table") match { case t:TableType => t.elements(4).applyLang(lang_id)}

		Log.info("init:operation_id:" + operation_id)
        DAO_OPERATION_001.findAll() match { 
            case Nil => DAO_OPERATION_001
                          .create
                          .operation_nm("system")
                          .status(1)
                          .organization_nm("uca")
                          .login_nm(1)
                          .description("create automatico")
                          .update_dt(new Date)
                          .delete_fg(0)
                          .save
            case _ => None
        }
    }

  /**
   * Implimir parte de titulo by Frania
   */ 
	def title = {
     	<H2>{odo.body("title").toLabel(lang_id)}</H2>     
	}

  /**
   * Implimir parte de cabesa by Frania
   */
	def head = {
		<table>
		{odo.body("operation_nm").toTableElement(lang_id)}
		{odo.body("description").toTableElement(lang_id)}
		</table>
	}

  /**
   * Implimir tabla by Carlos Delgalillo
   */
	def table={
    	<table>		
        { odo.body("table") match { case t:TableType => t.mkTableTitle(lang_id) }}
		<lift:BL_OPR002.listOp>
        { odo.body("table") match { case t:TableType => t.mkTableEmbed()} }
        </lift:BL_OPR002.listOp>
        {addForm() }
        </table>
		
	}
	
/**
 * boton de transaction by Frania
 */ 
    def transaction():NodeSeq ={
		<table>
		  <tr>
		      <td><OPR002:submit><button>SAVE</button></OPR002:submit></td>
		      <td><OPR002:cancel><button>CANCEL</button></OPR002:cancel></td>
		  </tr>
		</table>
    }
	
   
  /**
   * Mostrar pagina major by Frania
   */ 
	 def display(form: NodeSeq) ={
       { if (form.toString.length > 0) { mode = form.toString.toInt}; Log.info("display mode=" + mode) }
        {init()}
     <lift:BL_OPR002.execute form="post" multipart="true">	
	       {head}
	       {table}
	       {transaction}
     </lift:BL_OPR002.execute>
	 }
	
  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
    	val input_oprnm = DAO_OPERATION_001.create

        // va a consultar OPERATION para mode
    	val output_oprnm:DAO_OPERATION_001= {
            DAO_OPERATION_001.getOperationForMode(operation_id, mode) match { 
                case (a:DAO_OPERATION_001)::Nil => a
                case Nil => DAO_OPERATION_001.create
		                  .operation_nm("")
		                  .description("")
                          .organization_nm("uca")
                          .login_nm(1)
            }
    	}
    	
    	odo.body("operation_nm").setValue(output_oprnm.operation_nm)
    	odo.body("description").setValue(output_oprnm.description)

      /**
       * doBind in execute 
       */ 
    	def doBind(form: NodeSeq):NodeSeq = {
			//Log.error("LOGTEST");
    		def submitContents:NodeSeq = {
		    	mode match {
		    	case 1 => SHtml.submit("OK", gotoOperationList)
		    	case 2 => SHtml.submit("SAVE",saveOperation)
		    	case 3 => SHtml.submit("SAVE",saveOperation) 
		    	case 4 => SHtml.submit("Delete",deleteOperation)
		    	case 5 => SHtml.submit("Apply", undoOperation)
		    	case 6 => SHtml.submit("APPROVE",appOperation)
		    	case _ => <DIV>ERROR</DIV>
		    	}
		    }

    		def submitCancel:NodeSeq = {
		    	mode match {
		    	case 1 => <DIV />
		    	case 2 => SHtml.submit("Cancel", cancelOperation)
		    	case 3 => SHtml.submit("Cancel", cancelOperation) 
		    	case 4 => SHtml.submit("Cancel", gotoOperationList)
		    	case 5 => SHtml.submit("Cancel", gotoOperationList)
		    	case 6 => SHtml.submit("Cancel", gotoOperationList)
		    	case _ => <DIV>ERROR</DIV>
		    	}
		    }

		    bind ("OPR002", form,	
		    	"operation_nm" -> odo.body("operation_nm").toForm(mode),
		    	"description" -> odo.body("description").toForm(mode), 
		    	"submit" -> submitContents,
		    	"cancel" -> submitCancel)
    		}	
		
       def saveOperation():(() => Any) ={
         if (odo.body("operation_nm").getValue.toString.equals("")) { 
            S.error("Tiene que entrar " + odo.body("operation_nm").toLabel(2))
            odo.body("operation_nm").getValue
         } else { 
//	       DAO_OPERATION_DATA_001.saveOperationData(operation_id)
           DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id,1).map(_.delete_!) // En caso de modificacion va a eliminar copia 
           updateModifiedList(2,1)
	       DAO_OPERATION_001.updateOperation(operation_id, mode)
           Session.messageVar() 
           S.redirectTo("/SYS/INF001")
           //          S.redirectTo("/Operation/OPR002ADD")
         }   
    
       } // end saveOperation

       def deleteOperation()={
    	   DAO_OPERATION_001.updateOperation(operation_id, 4)
       }
       
        
//       Nuevo
       def undoOperation()={
    	  DAO_OPERATION_DATA_001.undoOperationData(operation_id, mode)
    	   
    	   Session.messageVar()
    	   S.redirectTo("/SYS/INF001")
       }
       def appOperation()={
    	 DAO_OPERATION_DATA_001.approveOperationData(operation_id)
    	 DAO_OPERATION_001.updateOperation(operation_id, mode)
    	 Session.messageVar() 
         S.redirectTo("/SYS/INF001")
      }
      def gotoOperationList()={ 
          S.redirectTo("/Operation/OPR001REF")
      }

      /**
       * cancelボタン押下時呼び出される処理
       */
      def cancelOperation()={ 
    	 
         //DAO_OPERATION_001.CancelOpr(operation_id, mode)
         S.redirectTo("/Operation/OPR001REF")     
      }

    	doBind(form)

    }
    
    /**
     * 一覧より選択された業務データを削除する。
     * 
     */
    def deleteOperationData()={
    	 updateModifiedList(2,2)
        	list.map(a => 
        	{     
        		if (a.elements(0).checked == true){
        			var option = a.elements(0).getValue.toString.toInt
        			DAO_OPERATION_DATA_001.deleteOperationData(operation_id, option)
        			}
        	})
    }
    
  /**
   * Crear una lista de TableType que tiene contenido de DAO_OPERATION_DATA_001 
   * by Kaoru Sato
   * 業務データTableTypeのlistを作る
   */
    var list = {
      // listを作成するためのメソッド。
      def convert(d:DAO_OPERATION_DATA_001, o:TableType):TableType = { 
    	  
          o.elements(0).setValue(d.order)
          o.elements(1).setValue(d.data_nm)
          o.elements(2).setValue(d.data_type) //DAO_SYSTEM_VALIDATION_001.toLabel("v_type", d.data_type, lang_id))
          o.elements(3).setValue(d.size)
          o.elements(4).setValue(d.null_allowed) //DAO_SYSTEM_VALIDATION_001.toLabel("v_null_allow", d.null_allowed, lang_id))
		Log.info("Data name= "+d.data_nm + " size=" + d.size + "TableType:" + o.toString)
        o
      }
      /**
       *
       */ 
      val search:List[DAO_OPERATION_DATA_001] = {
        Log.info("searchlist mode =" + mode)
          val opr = DAO_OPERATION_001.getOperationForMode(operation_id, mode)
          Log.info("SELECTED OPERATION :" + opr.toString)
          opr match {
            case (a:DAO_OPERATION_001)::Nil => { 
              var actual = -1
              mode match { 
                // Ref
                case 1 => a.status.toInt match { 
                    case 1 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 1) // Create => copy
                          // Modified => actual Delete => actual Approved => actual
                    case 0|2|3 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 0) 
//                    case _ => Log.info("ERROR"); None // ERROR
                }
                // Add
                case 2 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 2)  // Tmp
                // Del
                case 4 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 0)  // 
                // Undo App
                case 5|6 => a.status.toInt match { 
                    case 1|2 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 1)  // 
                    case 3 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 0)  // 
                }
                // Mod
                case 3 => { 
                      // Si existe temp(OPERATION.actual==2) seleccionara 2
                      val opData = DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 2)
                      if (opData.size > 0) opData else { 
                        a.status.toInt match { 
                          case 1 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 1)  // Create => copy
                          case 2 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 1) // Approved => actual // Modified => actual
                          case 0 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id, 0) // Approved => actual
                        } // status match
                      } // end of if
                } // match 3
              }  // mode match
            } //case (a:DAO_OPERATION_001)::Nil
            case _ => Nil
          }
      }
      // 検索された
      search.map(a => convert(a, odo.body("table") match {case t:TableType => t.copy  }))

    } // end list

  /**
   * listに格納されている各要素(業務データ)をデータベースに反映する。
   *
   */ 
  def updateModifiedList(actualOld: Int, actualNew: Int)={ 
      list.map(a => 
        {     
          Log.debug("updateModifiedList operation_id:" + operation_id+ " data_nm:"+ a.elements(1).getValue.toString+ " order:"+ a.elements(0).getValue.toString +"checked"+ a.elements(0).checked)
          DAO_OPERATION_DATA_001.saveOperationDataWithValue(
          operation_id,
          a.elements(1).getValue.toString, // data_nm
          a.elements(2).getRealValue.toString.toInt, // data_type
          a.elements(3).getValue.toString.toInt, // size
          a.elements(4).getRealValue.toString.toInt, // null_allowed
          a.elements(0).getValue.toString.toLong, // order
          actualOld,
          actualNew)} 
      )
  } // end updateModifiedList

  /**
   * substituir HTML como valor o formulario by Carlos Delgalillo
   * applyボタンに対応した処理
   */ 
    def applyForm (form: NodeSeq, d:TableType) = { 
		bind("OPR002", form,
			 "data_order" -> d(0).toForm(mode), //.getValue.toString, 
			 "data_nm" -> d(1).toForm(mode), //.getValue.toString, 
			 "data_type" -> d(2).toForm(mode),
			 "size" -> d(3).toForm(mode), //.getValue.toString,
			 "null_allowed" -> d(4).toForm(mode)) //.getValue.toString)
    }

  /***
   * Implimir tabla como con valor o formulario by Carlos Delgalillo
   */ 
	def listOp(form: NodeSeq) = list.flatMap( d => applyForm(form, d))

    /**
     * El method para ejecutar cuando oprimir boton de "Add" o "Delete"
     * 追加、削除ボタンに対応した処理
     */
    def addData(form: NodeSeq) ={ 
        
        bind("OPR002", applyForm(form, odo.body("table").asInstanceOf[TableType]), 
             "add" -> SHtml.submit("add", addOperationData),
             "delete" -> SHtml.submit("delete", deleteOperationData))
    }

    /**
     * El method para agregar operation_data
     * operation_dataの追加メソッド
     */ 
    def addOperationData() ={
        // para copiar todos los elementos como temp
        // 既存の業務データをtemp状態にコピー
        // キャンセル時にtempデータを削除すれば編集前のデータを復帰できるように編集中のデータはtempとする
        updateModifiedList(2,2)
        var data_nm:String = ""
        var data_type:Int = 0
        var size:Int = 0
        var null_allowed:Int = 0
        val order:Long = 1
        val actual=2 // tmp
        try{
            odo.body("table") match 
            { case a:TableType => 
                { 
                  data_nm = a.elements(1).getValue.toString //data_nm
                  data_type = a.elements(2).getRealValue.toString.toInt  // data_type
                  size = a.elements(3).getValue.toString.toInt
                  null_allowed = a.elements(4).getRealValue.toString.toInt
                }  
           } 
//      
          // operation_dataの追加
          DAO_OPERATION_DATA_001.addOperationData(
                odo.body("operation_nm").getValue.toString,
                odo.body("description").getValue.toString,
                data_nm, 
                data_type, 
                size,
                null_allowed,  
                order, 
                actual)
          // operation_idをセッション変数へ保存
          Session.operation_idVar({ 
            DAO_OPERATION_001.findAll(By(DAO_OPERATION_001.operation_nm, 
                   odo.body("operation_nm").getValue.toString)) match {
                       case (a:DAO_OPERATION_001)::Nil => a.id.toString.toInt
                       case _ => S.error("addOperationData:fail "); -1
                   }
          })
        } catch{
    		  case e:Exception => Log.debug("addOperationData:operation_nm" + odo.body("operation_nm").getValue.toString + " data_nm:" + data_nm + " data_type:" + data_type + " size:" + size + " null_allowed:" + null_allowed + " order:" + order + "actual:" + actual);S.error("INVALID VALUES DATA WAS NOT ADDED");	
   	    }
    } // end addOperationData
/*=======
        DAO_OPERATION_DATA_001.AddOperationData("add_user", "description", "data_nm2", 1, 1, 1, 2)
    }*/
    
  /**
   * button add y formulario para modo de agregar(2) y modificar(3) by Kaoru Sato
   * 追加モード、変更モードの時、データ追加ボタンを押下したときに呼び出される処理。
   * This is a method to create "add button" when the mode is Add(2) or Mod(3)
   */
    def addForm()= {
      // モードの選択
      mode match { 
        case 2 | 3 => {
        { odo.body("table") match { case t:TableType => t.clear()} }
          <lift:BL_OPR002.addData> 
        { odo.body("table") match { case t:TableType => t.mkTableEmbed()} }
		  <tr>
		      <td><OPR002:add><button>ADD</button></OPR002:add></td>
		      <td><OPR002:delete><button>DELETE</button></OPR002:delete></td>
		  </tr>
        </lift:BL_OPR002.addData>
        }
        case _ => <div />
      }
    } // end addForm
}
