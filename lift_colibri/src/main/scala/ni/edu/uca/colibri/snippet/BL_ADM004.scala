package ni.edu.uca.colibri.snippet

import _root_.scala.xml.{NodeSeq, Text}
import _root_.ni.edu.uca.colibri.model._
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
import _root_.ni.edu.uca.colibri.dao._
import net.liftweb.common.Logger
import _root_.java.util.Date
import _root_.ni.edu.uca.colibri.ido._
import _root_.ni.edu.uca.colibri.odo._
import _root_.ni.edu.uca.colibri.fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._

class BL_ADM004 {
	object Log extends Logger
	var mode=2
	var odo= ODO_ADM004
	var lang_id:Int= 1		
	var operation_id = -1
	var page_id= -1
	var list:List[TableType] = Nil
	operation_id = Session.operation_idVar.get.toInt  
	page_id = Session.page_idVar.get.toInt	
    mode = Session.modeVar.get.toInt  
    
    def init():Unit = { 
        odo.setOptions(lang_id, operation_id)
        Log.info("init:operation_id:" + operation_id)
         	
        if (DAO_PAGE_001.findAll(By(DAO_PAGE_001.id, page_id)).size == 0)
        {
        	 var page = DAO_PAGE_001.create
        				page
        				.page_nm("")
        				.operation_id(operation_id)
        				.status(4)
        				.organization_nm("uca")
        				.login_nm(1)
        				.update_dt(new java.util.Date())
        				.panel_id(0)
        				.page_tp(0)
        				.save
        				page_id = page.id.toInt
                	
				        Log.info("init:page_id="+page_id + "page.id=" +page.id.toInt)
				        DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id)) match {
				        	case Nil => {DAO_OPERATION_DATA_001.getDetail(operation_id)
				        				match {
				        						case list:List[DAO_OPERATION_DATA_001] =>
				        							list.map(a => DAO_PAGE_ELEMENTS_001.create
										                          .page_id(page_id)
										                          .data_id(a.id)
										                          .label_nm("nbsp")
										                          .control_id(1)
										                          .size(a.size)
										                          .maxlength(a.size)
										                          .note("")
										                          .actual(2)
										                          .order(a.order)
										                          .save  
										               		)
										               		
				        						case _ => None
				        					}
				        	}
				        	case _ => None
        				}
        }        	
     }
	
	def title = {
        {init()}
     	<H2>{odo.body("title").toLabel(lang_id)}</H2>     
	}

  /**
   * Implimir parte de cabesa by Frania
   */
	def head = {
		<table>
		{odo.body("page_nm").listOptions = DAO_LABEL_001.getSelectListLabelForPage (operation_id, lang_id)}
		{odo.body("page_nm").toTableElement(lang_id)}
		</table>
	}
	
	def table={
		<table>
        { odo.body("table") match { case t:TableType => t.mkTableTitle(lang_id) }}
		<lift:BL_ADM004.listOp>
        { odo.body("table") match { case t:TableType => t.mkTableEmbed()} }
        </lift:BL_ADM004.listOp>
        {addForm()}
        </table>
	}

	def transaction():NodeSeq ={
		<table>
		  <tr>
		      <td><ADM004:submit><button>SAVE</button></ADM004:submit></td>
		      <td><ADM004:crearpaginalista><button>CREAR PAGINA DE LISTA</button></ADM004:crearpaginalista></td>
		      <td><ADM004:crearpaginadetalle><button>CREAR PAGINA DE DETALLE</button></ADM004:crearpaginadetalle></td>
		      <td><ADM004:cancelar><button>CANCELAR</button></ADM004:cancelar></td> 
		  </tr>
		</table>
    }
	 
	def display(form: NodeSeq) ={
     <lift:BL_ADM004.execute form="post" multipart="true">	
	       {head}
	       {table}
	       {transaction}
     </lift:BL_ADM004.execute>
	 }
	
  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
//    	val input_pgnm = DAO_OPERATION_001.create

        // va a consultar OPERATION para mode
    	val output_pgnm:DAO_PAGE_001= {
            DAO_PAGE_001.getPageForMode(page_id, mode) match { 
                case (a:DAO_PAGE_001)::Nil => a
                case Nil => DAO_PAGE_001.create
		                  .page_nm("")
                          .organization_nm("uca")
                          .login_nm(1)
            }
    	}
    	
    	odo.body("page_nm").setValue(output_pgnm.page_nm)
//    	odo.body("description").setValue(output_oprnm.description)
    
    	def doBind(form: NodeSeq):NodeSeq = {
			//Log.error("LOGTEST");

		    bind ("ADM004", form,	
		    	"page_nm" -> odo.body("page_nm").toForm(mode),
//		    	"description" -> odo.body("description").toForm(mode), 
		    	"submit" -> SHtml.submit("SAVE",savePage),
		    	"crearpaginalista" -> SHtml.submit("CREAR PAGINA DE LISTA", savePage1),
		    	"crearpaginadetalle" -> SHtml.submit("CREAR PAGINA DE DETALLE", savePage2),
		    	"cancelar" -> SHtml.submit("CANCELAR", cancelPageList))
		} // end doBind in execute

	  /**
       * 保存して戻る
       */ 
       def savePage():(() => Any) ={
         if (odo.body("page_nm").getValue.toString.equals("")) { 
            S.error("Tiene que entrar " + odo.body("page_nm").toLabel(2))
            odo.body("page_nm").getValue
         } else { 
           DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id,1).map(_.delete_!) // En caso de modificacion va a eliminar copia 
           updateModifiedList(2,1)
	       DAO_PAGE_001.updatePage(odo.body("page_nm").getValue.toString, page_id, mode)
           Session.messageVar() 
           Session.operation_idVar(operation_id)
           Session.modeVar(mode)
           S.redirectTo("/AdminPage/ADM001REF")
         }   
       } // end savePage in execute

      /**
       * リスト画面作成
       */ 
         def savePage1():(() => Any) ={
         if (odo.body("page_nm").getValue.toString.equals("")) { 
            S.error("Tiene que entrar " + odo.body("page_nm").toLabel(2))
            odo.body("page_nm").getValue
         } else { 
           DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id,1).map(_.delete_!) // En caso de modificacion va a eliminar copia 
           updateModifiedList(2,1)
	       DAO_PAGE_001.updatePage(odo.body("page_nm").getValue.toString,page_id, mode)
           Session.messageVar() 
           Session.operation_idVar(operation_id)
           Session.modeVar(mode)
           Session.page_idVar(page_id)
           S.redirectTo("/AdminPage/ADM005")
         }   
       } // end savePage in execute

      /**
       * 詳細画面作成
       */ 
         def savePage2():(() => Any) ={
         if (odo.body("page_nm").getValue.toString.equals("")) { 
            S.error("Tiene que entrar " + odo.body("page_nm").toLabel(2))
            odo.body("page_nm").getValue
         } else { 
           DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id,1).map(_.delete_!) // En caso de modificacion va a eliminar copia 
           updateModifiedList(2,1)
	       DAO_PAGE_001.updatePage(odo.body("page_nm").getValue.toString,page_id, mode)
           Session.messageVar() 
           Session.operation_idVar(operation_id)
           Session.modeVar(mode)
           Session.page_idVar(page_id)
           S.redirectTo("/AdminPage/ADM006")
         }   
       } // end savePage in execute

       def deletePage()={
    	   //DAO_PAGE_001.updatePage(page_id, 4)
       }
//       Nuevo
       def undoOperation()={
//    	   DAO_OPERATION_DATA_001.undoOperationData(page_id, mode)
    	   
//    	   Session.messageVar()
//    	   S.redirectTo("/SYS/INF001")
       }
       def appOperation()={
//    	 DAO_OPERATION_DATA_001.approveOperationData(page_id)
//    	 DAO_OPERATION_001.updateOperation(page_id, mode)
//    	 Session.messageVar() 
//         S.redirectTo("/SYS/INF001")
      }
      def gotoPageList()={ 
          S.redirectTo("/PageAdmin/ADM001REF")
      }

      def cancelPageList()={ 
    	 DAO_PAGE_001.CancelPageList(page_id, mode)    	 
         S.redirectTo("/AdminPage/ADM001REF")     
      } // end cancelPageList

    	doBind(form)

    }

	 
	def listOp(form: NodeSeq) = {
	  list = {
		def convert(d:DAO_PAGE_ELEMENTS_001, o:TableType):TableType = { 
          o.elements(0).setValue(d.order)
          o.elements(1).setValue(d.label_nm)
          o.elements(2).setValue(d.data_id)
          o.elements(3).setValue(d.control_id)
          o.elements(4).setValue(d.size)
          o.elements(5).setValue(d.maxlength)
          o.elements(6).setValue(d.note)
          Log.info("listOp:list:" + o.elements.toString)
          o
      }
 /**********************************************************************
  *      
  */
	  val search:List[DAO_PAGE_ELEMENTS_001] = {
          val page = DAO_PAGE_001.getPageForMode(page_id, mode)
          Log.info("Contenido PAGE: page_id="+ page_id+ " mode=" +mode+ " size="+page.size + " "+page.toString)
          page match {
            case (a:DAO_PAGE_001)::Nil => { 
              var actual = -1
              mode match { 
                // Add
                case 2 => DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id, 2)  // Tmp
                // Mod
                case 3 => a.status.toInt match { 
                    case 1 => DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id, 1)  // Create => copy
                    case 0 => DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id, 0) // Approved => actual
                  DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id, 0) // Approved => actual
                    case 2 => DAO_PAGE_ELEMENTS_001.getElementsByActual(page_id, 1) // Approved => actual // Modified => actual
                } // case 3
              }  // mode match
            } //case (a:DAO_PAGE_001)::Nil
            case _ => Nil
          }
        }
        search.map(a => convert(a, odo.body("table") match {case t:TableType => t.copy  }))
      }
	 Log.info("LISTA: size="+list.size +":"+ list.toString)

	  list.flatMap( d => applyForm(form, d))
	}
	
	 def addForm()= { 
      mode match { 
        case 2 | 3 => {
        	{odo.body("addTable") match { case t:TableType => t.elements(1).listOptions = DAO_LABEL_001.getSelectListLabel (operation_id, lang_id)}}
	        {odo.body("addTable") match { case t:TableType => t.elements(2).listOptions = DAO_OPERATION_DATA_001.getSelectListData_nm (operation_id)}}
	        { odo.body("addTable") match { case t:TableType => t.clear()} }
	          <lift:BL_ADM004.addData> 
	        { odo.body("addTable") match { case t:TableType => t.mkTableEmbed()} }
			  <tr>
		      <td><ADM004:add><button>ADD</button></ADM004:add></td>
		       
		  </tr>
        </lift:BL_ADM004.addData>
        }
        case _ => <div />
      }
    }
	 
	def addData(form: NodeSeq) ={ 
        bind("ADM004", applyForm(form, odo.body("addTable").asInstanceOf[TableType]), 
             "add" -> SHtml.submit("add", add_page_elements))
             
	}
	 def updateModifiedList(actualOld: Int, actualNew: Int)={ 
      list.map(a => 
        {     
          Log.debug("updateModifiedList operation_id:" + page_id+ " label_nm:"+ a.elements(1).getValue.toString+ " order:"+ a.elements(0).getValue.toString+" maxlength: "+ a.elements(5).getValue.toString)
          DAO_PAGE_ELEMENTS_001.savePageElementsWithValue(
          page_id,
          a.elements(1).getValue.toString, //label_nm
          a.elements(2).getRealValue.toString.toInt, // data_id
          a.elements(3).getRealValue.toString.toInt, // control_id
          a.elements(4).getValue.toString.toInt, // size
          a.elements(5).getValue.toString.toInt, // maxlength
          a.elements(6).getValue.toString, //note
          a.elements(0).getValue.toString.toLong, // order
          actualOld,
          actualNew)} 
      )
  }
	
	
	def add_page_elements() = {
        // para copiar todos los elementos como temp
        updateModifiedList(2,2)
        var label_nm:String = ""
        var data_id:Int = 0
        var control_id:Int = 0
        var size:Long =0
        var maxlength: Long= 1
        var note:String = ""
        val order:Long = 1
        val actual=2 // tmp
     
        try{
            odo.body("addTable") match 
            { case a:TableType => 
                { 
                  label_nm = a.elements(1).getValue.toString //label_nm
                  data_id = a.elements(2).getRealValue.toString.toInt  // data_id
                  control_id=  a.elements(3).getRealValue.toString.toInt  //control_id
                  size = a.elements(4).getValue.toString.toLong // size
                  maxlength = a.elements(5).getValue.toString.toLong // maxlength
                  note = a.elements(6).getValue.toString // note
                  Log.info("datos de prueba: "+ label_nm +""+data_id.toString+""+ control_id.toString+""+ size.toString+""+ maxlength.toString+""+ note)
                }  
           } 
      
          DAO_PAGE_ELEMENTS_001.addPageElements(odo.body("page_nm").getValue.toString,label_nm, data_id,
                                                control_id, size, maxlength, note,actual, operation_id, page_id )
          Session.page_idVar(page_id)
        } catch{
    		  case e:Exception => Log.debug("addPageElements:page_nm" + odo.body("page_nm").getValue.toString + " label_nm:" + label_nm + " data_id:" + data_id + "control_id: "+ control_id + " size:" + size + " maxlength:" + maxlength + "note: "+ note + " order:" + order + "actual:" + actual);S.error("INVALID VALUES ELEMENT WAS NOT ADDED");	
   	    }
        
    }
		
	
	def delete_page_elements()={
		list.map(a=>
		{
			if (a.elements(0).checked == true){
				var option = a.elements(0).getValue.toString.toInt
				DAO_PAGE_ELEMENTS_001.deletePageElements(page_id, option)
			}
			
		})
	}
	
	
	
	
	def applyForm (form: NodeSeq, d:TableType) = { 
		bind("ADM004", form,
			 "elm_order" -> d(0).toForm(mode), //.getValue.toString, 
			 "elm_label" -> d(1).toForm(mode), //.getValue.toString, 
			 "elm_name" -> d(2).toForm(mode),
			 "elm_control" -> d(3).toForm(mode), //.getValue.toString,
			 "elm_size" -> d(4).toForm(mode), //.getValue.toString)
			 "elm_max" -> d(5).toForm(mode), //.getValue.toString)
			 "elm_note" -> d(6).toForm(mode)) //.getValue.toString)
    }
	   
}
