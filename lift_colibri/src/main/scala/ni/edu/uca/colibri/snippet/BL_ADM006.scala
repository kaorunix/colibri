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
//import java.util._
import ido._
import odo._
import fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._


class BL_ADM006 {
		
	object Log extends Logger
	var odo= ODO_ADM006
	var lang_id:Int= 1
	var operation_id = "-1"
	var page_id = "-1"
    val page_tp = "2" // detail page
    var mode="0"
    var list:ListTableType=null
    mode = Session.modeVar.get.toString
    operation_id = Session.operation_idVar.get.toString
    page_id = Session.page_idVar.get.toString
/**
 * Initializar by Kaoru Sato
 */ 
    def init():Unit = { 
        val page = DAO_PAGE_001.getPageForMode(page_id.toInt,mode.toInt)
        if (page.size > 0) { 
          odo.body("page_nm").setValue(page(0).page_nm.toString)
          odo.body("page_nm").label_nm = page(0).page_nm.toString
          Log.info("BL_ADM006:init:page_nm="+ odo.body("page_nm").getValue.toString)
        } // if
    } // init

  /**
   * Implimir titulo by Kaoru Sato
   */ 
	def title = {
        {init()}
     	<H2>{odo.body("title").toLabel(lang_id)}</H2>     
	} // title

  /**
   * Implimir titulo by Kaoru Sato
   */ 
	def modtitle = {
     	<H3>{odo.body("page_nm").toLabel(lang_id)}</H3>
	} // modtitile

  /**
   * Implimir tablas by Kaoru Sato
   */
	def table = {
		<table>
        { odo.body("contents") match { case t:TableType => t.mkTableTitle(lang_id) }}
		<lift:BL_ADM006.listOp>
        { Log.info("table:surrounded by listOp")
          odo.body("contents") match { case t:TableType => t.mkTableEmbed()} }
        </lift:BL_ADM006.listOp>
		</table>
	} // table

//TODO テーブルの最後に各パネルのための追加フォームを追加する  {addForm() }

    /**
     * initPanel
     * 選択されたページにパネルとテーブル要素が存在していない場合ページ要素よりパネルを作成する
     */ 
    def initPanel() = { 
        Log.info("initPanel:page_id:"+page_id+" operation_id:" + operation_id)

        val page = DAO_PAGE_001.findAll(By(DAO_PAGE_001.id, page_id.toLong)) match { case (a:DAO_PAGE_001)::Nil => a}
        if (DAO_PANEL_001.findAll(By(DAO_PANEL_001.page_id, page_id.toLong)).size == 0)
        {
        	var panel = DAO_PANEL_001.create
                        
        				panel.page_id(page)
        				.order(1)
        				.save
//            panel.find()
        	val	panel_id:Long = panel.id
                	
	        Log.info("initPanel:panel_id="+panel_id + " page.id=" +page_id)
            // TABLE_CONTENTSに同じpanel_idがないことを確認
	        DAO_TABLE_CONTENTS_001.findAll(By(DAO_TABLE_CONTENTS_001.panel_id, panel_id)) match {
			    case Nil => {DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page.id))
			                 match {
                               case page_elements_list:List[DAO_PAGE_ELEMENTS_001] =>
				        			page_elements_list.map(
                                        a => DAO_TABLE_CONTENTS_001.create
										        .panel_id(panel_id)
										        .element_id(a.id)
										        .mode(1) // all
		                                        .order(a.order)
										        .save  
									)
				        	   case _ => None
				        	 } // end match DAO_PAGE_ELEMENTS_001
				           } // end case Nil
			  case _ => None // Errorを返すように作り変える必要あり。
        	}// end match DAO_TABLE_CONTENTS_001
          } // end if DAO_PANEL_001      	
    } // end initPanel

  // initPanelはpanelListを作成する前に実行する
    initPanel()

  /**
   * mkLabData in convert in panelList
   */
  def mkLabData(element_id:Long):String = { 
    val page_element = DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.id, element_id)) match { 
      case (a:DAO_PAGE_ELEMENTS_001)::Nil => a 
      //              case _ => None
    }
    val op_data = DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.id, page_element.data_id)) match { 
      case (a:DAO_OPERATION_DATA_001)::Nil => a
//              case _ => None
    }
    page_element.label_nm + ":" + op_data.data_nm
  } // end mkLabData



  /**
   * convTable in convert in panelList
   * panel_idよりListTableTypeに値を詰め込む
   */ 
  def convTable(panel_id:Long, t:ListTableType) :Unit ={ 
    // tc TABLE_CONTENTSの要素
    val tc = DAO_TABLE_CONTENTS_001.findAll(
      By(DAO_TABLE_CONTENTS_001.panel_id, panel_id),
      OrderBy(DAO_TABLE_CONTENTS_001.order, Ascending))
    var title_list = tc.map(a => (mkLabData(a.element_id.toLong), a.element_id.toString))
    Log.info("TABLE_CONTENTS count:" + tc.size)
    Log.info("TABLE_CONTENTS data:" + tc.toString + " END TABLE_CONTENTS data")
    tc.map(a => { 
      var newlist = t.getCopyClearElements()
      Log.info("panelList-newlist:" + newlist.toString)
      newlist(0).setValue(a.order.toInt) //con_order
      newlist(1).setValue(mkLabData(a.element_id.toLong)) //con_element
      newlist(1).listOptions = title_list
      newlist(2).setValue(
        DAO_PAGE_ELEMENTS_001.findAll(
          By(DAO_PAGE_ELEMENTS_001.id, a.element_id)) 
            match { 
              case (b:DAO_PAGE_ELEMENTS_001)::Nil => { 
                Log.info("newlist(2):" + b.control_id)
                b.control_id.toInt 
              }
              case _ => 0
            }
      )    // con_control
      newlist(3).setValue(a.mode) // con_mode
      newlist(4).setValue(a.id) // con_id
      Log.info("panelList-newlist AFTER:" + t.toString )
      t.addList(newlist)
    })
  } // end convTable

  /**
   * convAddTable in convert in panelList
   * panel_idよりListTableTypeに値を詰め込む
   */ 
  def convAddTable(panel_id:Long, t:ListTableType) :Unit ={ 
    // tc TABLE_CONTENTSの要素
    val tc = DAO_TABLE_CONTENTS_001.findAll(
      By(DAO_TABLE_CONTENTS_001.panel_id, panel_id),
      OrderBy(DAO_TABLE_CONTENTS_001.order, Ascending))
    var title_list = tc.map(a => (a.element_id.toString, mkLabData(a.element_id.toLong)))
    Log.info("convAddTable TABLE_CONTENTS data:" + tc.toString + " END TABLE_CONTENTS data")
    Log.info("t.elements="+t.elements.toString)
    
    t.elements(0)(1).listOptions = title_list
/*    t.elements(0)(2).setValue(
        DAO_PAGE_ELEMENTS_001.findAll(
          By(DAO_PAGE_ELEMENTS_001.id, a.element_id)) 
            match { 
              case (b:DAO_PAGE_ELEMENTS_001)::Nil => { 
                Log.info("newlist(2):" + b.control_id)
                b.control_id.toInt 
              }
              case _ => 0
            }
      ) */   // con_control
//      t.elements(0)(3).setValue(a.mode) // con_mode
      Log.info("convAddTable AFTER:" + t.toString + " elements:"+  t.elements(0).toString
    )
  } // end convTable


  // panelList: TableType[]を作る
    var panelList = { 
        /**
         * convert in panelList
         */
        def convert(d:DAO_PANEL_001, o:TableType):TableType = { 

          /* NOTE ここにconvTableがあった */

          Log.info("PANELLIST:0=" + o.elements(0).toString + " 1=" + o.elements(1).getValue() + " 2=" + o.elements(2).getValue())
          Log.info("ListTableType=" + o.elements(1).asInstanceOf[ListTableType].elements.map(_.toString))
          o.elements(0).setValue(d.order) // PANEL.order
          convTable(d.id, o.elements(1).asInstanceOf[ListTableType])
          o.elements(2).setValue(d.id)  // PANEL.panel_id
          o
        } // end convert

        // page: DAO_PANEL_001[] page_id  
        val page = DAO_PANEL_001.findAll(
          By(DAO_PANEL_001.page_id, page_id.toLong),
          OrderBy(DAO_PANEL_001.order, Ascending)) 
      Log.info("PANEL count:" + page.size)
        page.map(a => convert(a, odo.body("contents") match {case t:TableType => t.copy  }))
    } // end panelList


/**
 * boton de transaction by Kaoru Sato
 */ 
    def transaction():NodeSeq ={
		<table>
		  <tr>
		      <td><ADM006:submit><button>OK</button></ADM006:submit></td>
		  </tr>
		</table>
    }
	
  /**
   * Mostrar pagina major by Frania
   */ 
	 def display(form: NodeSeq) ={
     <lift:BL_ADM006.execute form="post" multipart="true">	
	       {table}
	       {transaction}
     </lift:BL_ADM006.execute>
	 }
	
  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
        /**
         * redirectADM004 in execute
         */
        def redirectADM004() = {
            Session.operation_idVar(operation_id.toLong)
            Session.modeVar(mode.toInt)
            mode match {
              case "1" => S.redirectTo("/AdminPage/ADM001UNDO")
              case "2" => S.redirectTo("/AdminPage/ADM001UNDO")
              case "3" => S.redirectTo("/AdminPage/ADM001UNDO")
              case "4" => S.redirectTo("/AdminPage/ADM001UNDO")
              case "5" => S.redirectTo("/AdminPage/ADM001UNDO")
              case "6" => S.redirectTo("/AdminPage/ADM001UNDO")
            }
            
        }

      /**
       * doBind in execute()
       */
    	def doBind(form: NodeSeq):NodeSeq = {
		    bind ("ADM006", form,	
//                "operation_id" -> SHtml.text("", operation_id = _),
//                "mode" -> SHtml.text("", mode = _),
		    	"submit" -> SHtml.submit("OK", savePage))
    	}	// doBind
	  /**
       * 保存して戻る
       */ 
       def savePage():(() => Any) ={
           updateModifiedList()
           Session.messageVar() 
           Session.operation_idVar(operation_id.toLong)
           Session.modeVar(mode.toInt)
           S.redirectTo("/SYS/INF001")
       }

    	doBind(form)

    } // execute

	def listOp(form: NodeSeq) = panelList.flatMap(d => applyContentsForm(form, d)) 
//          Log.info("listOp:panelList" + panelList.toString)


  /***
   * substituir HTML como valor o formulario by Carlos Delgalillo
   */ 
    def applyContentsForm (form: NodeSeq, d:TableType) = { 
		bind("ADM006", form,
			 "panel_order" -> d.elements(0).toForm(mode.toInt), //.getValue.toString, 
			 "contents" -> { 
               applyForm({ d.elements(1).asInstanceOf[ListTableType] }, d.elements(2).getValue.toString.toLong)
             }
           ) //.getValue.toString, 
    }

  var count_applyform = 0:Int // it must be taken off
  /**
   * テーブルの作成
   */
    def applyForm(t:ListTableType, panel_id:Long):NodeSeq= { 
      { list = t
       Log.info("applyForm:" + t.toString + " elements:" + t.elements.toString)
     }
      <table>
        { t.mkTableTitle(lang_id) }
		<lift:BL_ADM006.listTableOp>
        { 
          Log.info("applyform:listTableOp" + t.mkTableEmbed().toString)
          t.mkTableEmbed() 
       }
        </lift:BL_ADM006.listTableOp>
        { Log.info("applyForm")
//なんでここが何回も呼ばれるか?
          addForm(panel_id) }
      </table>
    }

  /**
   * listTableOp
   * listの要素に対してapplyTableFormを適用する
   */ 
	def listTableOp(form: NodeSeq) = list.elements.flatMap(d => applyTableForm(form, d)) 

//       Log.info("listTableOp: list.elements:" + list.elements.toString + " [element:" + list.elements.map(_.toString))

  /**
   * applyTableForm
   * テーブルの一行
   */ 
    def applyTableForm(form:NodeSeq, d:List[DataType]) = { 
        bind("ADM006", form,
             "con_order" -> d(0).toForm(mode.toInt),
             "con_element" -> d(1).toForm(mode.toInt),
             "con_control" -> d(2).toForm(mode.toInt),
             "con_mode" -> d(3).toForm(mode.toInt),
             "con_id" -> d(4).toForm(mode.toInt)
           )
    }

    /**
     * addForm
     * 追加ボタン用フォーム
     */ 
	 def addForm(panel_id:Long)= { 
       /**
        * getElements
        */
       def getElements(panel_id:Long)={ 
         // panel_idよりページ番号に対応したpanel_idを保持するtable_contentsよりpage_element_idを取得
         DAO_TABLE_CONTENTS_001.findAll(By(DAO_TABLE_CONTENTS_001.panel_id, panel_id))
                                 .map(a => (mkLabData(a.element_id), a.order.toString))
       }
      mode match { 
        // create mode or modify mode only
        case "2" | "3" => {
        	{odo.body("addTable") match { 
              // フォームのラベル:データ名に対応するセレクトを作成
                case t:ListTableType => { Log.info("addForm:mode match:t:"+ t.elements.map(_.toString +" + "))
                  //t.elements(0)(1).listOptions = getElements(panel_id)
                  convAddTable(panel_id,t)
                                       }
              }
            }

//	        {odo.body("addTable") match { case t:ListTableType => t.elements(2).listOptions = DAO_TABLE_CONTENTS_001.getSelectListData_nm (operation_id)}}
//	        { odo.body("addTable") match { case t:TableType => t.clear()} }
//              { //Log.info("ombed of addForm:" + t.toString + t.mkTableEmbed())}
/*	      <lift:BL_ADM006.addData panel_id={panel_id.toString}> */
	        { 
              odo.body("addTable") match { 
                case t:ListTableType => { 
	      <lift:BL_ADM006.addData panel_id={panel_id.toString}> 
                   { t.mkTableEmbed()} 
                  <tr>
		            <td><ADM006:add><button>ADD</button></ADM006:add></td>
		            <td><ADM006:del><button>DEL</button></ADM006:del></td>
		          </tr>
          </lift:BL_ADM006.addData>
                }
                case _ => <div>getElements ListTableType was espected.</div>
              }
            }
/*          </lift:BL_ADM006.addData>*/
        }
        case _ => <div>{mode }</div>
      }
    } // end addForm
  var icount=0

  /**
   * applyAddForm
   * テーブルの追加ボタン一行
   */ 
    def applyAddForm(form:NodeSeq, d:List[DataType]) = { 
//        Log.info("applyAddForm BEFORE:"+ d+ "con_order2="+ d(0).getRealValue + "con_element2=" + d(1).getRealValue + ":" + d(1).getValue +
//               "con_control=" + d(2).getRealValue + "con_mode=" + d(3).getRealValue + "con_id=" + d(4).getRealValue)
        val ret = bind("ADM006", form,
             "con_order2" -> d(0).toForm(mode.toInt),
             "con_element2" -> d(1).toForm(mode.toInt),
             "con_control" -> d(2).toForm(mode.toInt),
             "con_mode" -> d(3).toForm(mode.toInt),
             "con_id" -> d(4).toForm(mode.toInt)
           )
//        Log.info("applyAddForm AFTER:"+d+"con_order2="+ d(0).getRealValue + "con_element2=" + d(1).getRealValue +":" + d(1).getValue +
//               "con_control=" + d(2).getRealValue + "con_mode=" + d(3).getRealValue + "con_id=" + d(4).getRealValue)
        ret
    }
/*    def applyAddForm(t:ListTableType, panel_id:Long):NodeSeq= {
      <div />
    }*/

	def addData(form: NodeSeq) ={ 
      var l:Long = -1
      S.attr("panel_id").map(a=>{ l = a.toString.toLong})
      Log.info("addData:panel_id:" + S.attr("panel_id").toString)
        bind("ADM006", 
            {  icount+=1;Log.info("addData:call applyForm" + icount)
             applyAddForm(form, odo.body("addTable").asInstanceOf[ListTableType].elements(0).asInstanceOf[List[DataType]])}, 
             "add" -> SHtml.submit("add", addContents(l)),
             "del" -> SHtml.submit("del", delContents(l)))
             //(S.attr("panel_id").toString)
	}

	 def updateModifiedList()={ 
      panelList.map(a => 
        {     
          // page更新
          DAO_PAGE_001.updateForPageTp(page_id.toInt, page_tp.toInt)

          // 1パネルに含まれるコンテンツ列のリスト
          val contents = a.elements(1).asInstanceOf[ListTableType].elements

          Log.debug("updateModifiedList operation_id:" + page_id+ " label_nm:"+ a.elements(1).getValue.toString+ " order:"+ a.elements(0).getValue.toString)
          Log.debug("updateModifiedList contents:" + contents.toString) 

/*          DAO_PANEL_001.savePanelWithValue(
          page_id,
          a.elements(0).getValue.toString.toLong, //PANEL.order
          a.elements(2).getValue.toString.toLong, // PANEL.panel_id
          actualOld,
          actualNew)*/
          contents.map(b =>{ 
          Log.debug("updateModifiedList contents:panel_id:" + b(2).getValue + " element_id:" + b(1).getValue + " mode:" + b(3).getValue + " order:" + b(0).getValue + " contents_id:" + b(4).getValue) 
          DAO_TABLE_CONTENTS_001.saveTableContentsWithValue(
            a.elements(2).getValue.toString.toLong, //panel_id
//            b(1).getValue.toString.toLong, //element_id
            b(3).getRealValue.toString.toInt, // mode
            b(0).getValue.toString.toLong, // order
            b(4).getValue.toString.toLong // id
          )})
        } 
      )
  }

    def addContents(panel_id:Long)()={
//panel_id:String
//        var panel_id:Long = 0;
//        S.attr("panel_id").map(a=>{ panel_id = a.toString.toLong})
        Log.info("addContents: if panel_id have got:" + panel_id + ":"+S.attr("panel_id").toString)
        // para copiar todos los elementos como temp
        updateModifiedList()
        var con_order:Long = 0
        var con_element:Int = 0
        var con_mode:Int = 0
     
        try{
            val a = odo.body("addTable").asInstanceOf[ListTableType] 
                  Log.info("addContents:ListTableType:" + a.toString + 
                           " elements=" + a.elements + " elements(0)=" + a.elements(0) + "\n" +
                           " elements(0)(0)=" + a.elements(0)(0).getValue + 
                           " elements(0)(1)=" + a.elements(0)(1).getValue + 
                           " elements(0)(2)=" + a.elements(0)(2).getValue +
                           " elements(0)(3)=" + a.elements(0)(3).getValue + "\n" 
//                           " elements(1)(0)=" + a.elements(1)(0).getValue + 
//                           " elements(1)(1)=" + a.elements(1)(1).getValue + 
//                           " elements(1)(2)=" + a.elements(1)(2).getValue +
//                           " elements(1)(3)=" + a.elements(1)(3).getValue + "\n" +
//                           " elements(2)(0)=" + a.elements(2)(0).getValue + 
//                           " elements(2)(1)=" + a.elements(2)(1).getValue + 
//                           " elements(2)(2)=" + a.elements(2)(2).getValue +
//                           " elements(2)(3)=" + a.elements(2)(3).getValue + "\n"
                         )
                  Log.info("addTable: "+ con_order +":"+con_element+":"+ con_mode)
//                  con_order = a.elements(0)(0).getValue.toString.toLong //con_order
                  con_element = a.elements(0)(1).getRealValue.toString.toInt  // element
//                  con_control =  a.elements(3).getRealValue.toString.toInt  // control
                  con_mode = a.elements(0)(3).getRealValue.toString.toInt // mode
//                  con_id = a.elements(5).getValue.toString.toLong // id


      
          DAO_TABLE_CONTENTS_001.addTableContents(panel_id,con_element,con_mode)
//          Session.page_idVar(page_id.toLong)
//        } catch{
//    		  case e:Exception => Log.debug("addTableContents:panel_id=" + panel_id + " order:" + con_order + " element:" + con_element + " mode: "+ con_mode);S.error("INVALID VALUES TABLE_ELEMENT WAS NOT ADDED");	
   	    }
        
    }

	def delContents(panel_id:Long)()={
        var contents_ids:Seq[Long] = Nil
      /*
         panelList 
           [panel_order, table[con_order, con_element, con_control, con_mode, con_id], panel_id]
           [panel_order, table[con_order, con_element, con_control, con_mode, con_id], panel_id]
           [panel_order, table[con_order, con_element, con_control, con_mode, con_id], panel_id]
      */
        panelList.map(a => { 
          Log.info("delContents:panelList=" + a.toString + ":" + a.elements.map(b => "+" + b.toString))
          
              // パネルリストの中で削除ボタンが押されたパネルidだけ抜き出し
                // 削除ボタンが押されたパネルidでチェックボックスにチェックがあるものを抜き出し
          if (a.elements(2).getValue.toString.toLong == panel_id){ 
              a.elements(1).asInstanceOf[ListTableType].elements.map(b =>
                if (b(0).checked) { 
                  contents_ids = contents_ids ++ Seq(b(4).getValue.toString.toLong)
                  Log.info("elements(4)=" + contents_ids.toString)
                }
              )
          }
        }) // a.elements.map
	    DAO_TABLE_CONTENTS_001.delTableContents(panel_id, contents_ids)
	}


}
//			              c.elements(1).map(d => if (d(0).checked == true)
//				  contents_ids += d(4).getValue.toString.toLong
