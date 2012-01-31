package ni.edu.uca.colibri.fw

import _root_.java.io._
import ni.edu.uca.colibri.dao._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._

case class Generate(page_id:Long) {
  val pathprefix = "src\\main\\"
  object Log extends Logger

/*      val page = DAO_PAGE_001.findAll(By(DAO_PAGE_001.id, page_id)) match { 
          case (a:DAO_PAGE_001)::Nil => a
      }*/
//	  val panel = DAO_PANEL_001.findAll(By(DAO_PANEL_001.page_id, page_id))
	 
      val page_elements=DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id))

      //準備クラス
      val page = DAO_PAGE_001.findAll(By(DAO_PAGE_001.id, page_id)).head 
//                                      By(DAO_PAGE_001.operation_id, operation_id)).head
      val panel = DAO_PANEL_001.findAll(By(DAO_PANEL_001.page_id, page.id), 
                                      OrderBy(DAO_PANEL_001.order, Ascending))

      // panelの数分のList((DAO_PANEL_001, List[DAO_TABLE_CONTENTS_001])))を作る
      val tableContents = panel.map(a => (a, DAO_TABLE_CONTENTS_001.findAll(By(DAO_TABLE_CONTENTS_001.panel_id, a.id), OrderBy(DAO_TABLE_CONTENTS_001.order, Ascending)))) 

      Log.info("Generate:mkOdo:page="+ page.toString + " panel=" + panel.toString + " tableContents=" + tableContents.toString)

      // panel数分のList中にtable_contents数分のListで
      // 各element_idに対応したpage_elementsの作成 
      // List(DAO_PANEL_001, (List(DAO_TABLE_CONTENTS, List[DAO_PAGE_ELEMENTS_001])))
      val pageElements = tableContents.map(a => 
        a match { 
          case (panel_a:DAO_PANEL_001, 
         tableContents_list:List[DAO_TABLE_CONTENTS_001]) => 
           (panel_a, tableContents_list.map(tableCont => 
          (tableCont, DAO_PAGE_ELEMENTS_001.findAll(
              By(DAO_PAGE_ELEMENTS_001.id, tableCont.element_id)))))
           case _ => Nil
        })

      def pageEapply(f:((DAO_PANEL_001, DAO_PAGE_ELEMENTS_001, DAO_TABLE_CONTENTS_001) => String), delimiter:String)(filter_pid:Long= -1):String={ 
//        val implicit filter_pid:Long = -1
        pageElements.map(a => 
          a match { case (panel_i:DAO_PANEL_001, 
                        tableCs:(List[(DAO_TABLE_CONTENTS_001, List[DAO_PAGE_ELEMENTS_001])])) if (filter_pid == -1 || filter_pid == panel_i.id.toLong)=> 
                          tableCs.map(b => 
                            b match { case (tableC:DAO_TABLE_CONTENTS_001, 
                                            pageEs:List[DAO_PAGE_ELEMENTS_001]) =>
                                 pageEs.map(pageE => 
                                   f(panel_i, pageE, tableC)).mkString
                                      case _ => "TYPE ERROR1"
                                   }
                                    ).mkString(delimiter)
                   case (panel_i:DAO_PANEL_001, 
                        tableCs:(List[(DAO_TABLE_CONTENTS_001, List[DAO_PAGE_ELEMENTS_001])])) => 
                          Log.info("Generate:filter_pid=" + filter_pid + "panel_id=" + panel_i.id)
                          ""
                   case _ => "TYPE ERROR2"
                 }).mkString
      }

    def mkAll(page_id: Long){ 
//     def printObject(e:Elements):String = { //e.mapper
//       val m = Mapper
//       val c = Mapper.findAll(BySql("id =?", IHaveValidatedThisSQL("kaoru", "2011-03-18"), e.mapper))(0)
//       Log.info("Mapper:" + c.toString)
//       val s = ",64"
//       val s = c.size match { 
//           case i if i.toInt > 0 => "," + i.toString
//           case _ => ""
//       }
//       "    object " + e.column + " extends Mapped" + c.mapper + "(this" + s + ")"      

//     }

      mkOdo(page.page_nm, page.operation_id)
      (1 to 6).map(a => mkHtml(page.page_nm, a.toString))
      mkSnippet(page.page_nm, page.operation_id)
    }

	def mkFile (path :String, contents: String){
		try{
			  val bw = 
			    new BufferedWriter(new FileWriter(path));
			   bw.write(contents)
			   bw.close();
		}catch{
			case e: IOException  => println("IO exception");
			}
		}

	def mkHtml (pageName: String, mode:String){
		val html_file: String = pathprefix + "webapp\\Generate\\" + pageName + 
                  { mode match { case "1" => "REF" 
                               case "2" => "ADD"
                               case "3" => "MOD"
                               case "4" => "DEL"
                               case "5" => "UNDO"
                               case "6" => "APP"
                               case _ => ""}
                 } + ".html";
		val html_contents = "<div class=\"lift:surround?with=default;at=content\"> \n" +
			  				"   <lift:BL_" + pageName + ".title /> \n" +
                            "<HR></HR>\n" +
			  				"	<lift:BL_" + pageName + ".display>" + mode +
			  				"</lift:BL_"+ pageName +".display> \n" +
			  				"</div> \n"
		mkFile(html_file, html_contents)
	}

/*
class ODO_OPR001 {
    var title= DataType ("Operation",1) // PAGE=Operation
    var operation_id= DataType("nbsp",1) // OPERATION_DATA=operation_id PAGE_ELEMENTS=nbsp OPERATION_DATA=1
    var operation_nm= DataType ("Operation_Name", 1) // 
    var status= DataType ("Status", 1)
    var data_quantity= DataType ("Data_Quantity",1)
    var organization_nm= DataType ("Organization",1)
    var login_nm= DataType ("User",1)
    var update_dt= DataType ("DateTime",1)
    var mode= DataType ("Operation_Name",1)
    var ok_button= DataType ("ok_button", 1)

    var table = TableType ("table", 1)
    
    operation_id.getControl(1).setControl(4, 0, 0) //Control=PAGE_ELEMENTS
    operation_id.getControl(2).setControl(4, 0, 0)
    operation_id.getControl(3).setControl(4, 0, 0)
    operation_id.getControl(4).setControl(4, 0, 0)
    operation_id.getControl(5).setControl(4, 0, 0)
    operation_id.getControl(6).setControl(4, 0, 0)
        
    operation_nm.getControl(1).setControl(1, 0, 0)
    operation_nm.getControl(2).setControl(1, 0, 0)
    operation_nm.getControl(3).setControl(1, 0, 0)
    operation_nm.getControl(4).setControl(1, 0, 0)
    operation_nm.getControl(5).setControl(1, 0, 0)
    operation_nm.getControl(6).setControl(1, 0, 0)
    
    status.getControl(1).setControl(1,0,0)
    status.getControl(2).setControl(1,0,0)
    status.getControl(3).setControl(1,0,0)
    status.getControl(4).setControl(1,0,0)
    status.getControl(5).setControl(1,0,0)
    status.getControl(6).setControl(1,0,0)
    
    data_quantity.getControl(1).setControl(1,0,0)
    data_quantity.getControl(2).setControl(1,0,0)
    data_quantity.getControl(3).setControl(1,0,0)
    data_quantity.getControl(4).setControl(1,0,0)
    data_quantity.getControl(5).setControl(1,0,0)
    data_quantity.getControl(6).setControl(1,0,0)
    
    organization_nm.getControl(1).setControl(1,0,0)
    organization_nm.getControl(2).setControl(1,0,0)
    organization_nm.getControl(3).setControl(1,0,0)
    organization_nm.getControl(4).setControl(1,0,0)
    organization_nm.getControl(5).setControl(1,0,0)
    organization_nm.getControl(6).setControl(1,0,0)
    
    login_nm.getControl(1).setControl(1,0,0)
    login_nm.getControl(2).setControl(1,0,0)
    login_nm.getControl(3).setControl(1,0,0)
    login_nm.getControl(4).setControl(1,0,0)
    login_nm.getControl(5).setControl(1,0,0)
    login_nm.getControl(6).setControl(1,0,0)
    
    update_dt.getControl(1).setControl(1,0,0)
    update_dt.getControl(2).setControl(1,0,0)
    update_dt.getControl(3).setControl(1,0,0)
    update_dt.getControl(4).setControl(1,0,0)
    update_dt.getControl(5).setControl(1,0,0)
    update_dt.getControl(6).setControl(1,0,0)
    
    operation_id.embedItem = <OPR001:operation_id />
    operation_nm.embedItem= <OPR001:operation_nm />
    status.embedItem= <OPR001:status />
    data_quantity.embedItem= <OPR001:data_quantity />
    organization_nm.embedItem= <OPR001:organization />
    login_nm.embedItem= <OPR001:login_nm />
    update_dt.embedItem= <OPR001:date />
    status.realValue_fg = false

    status.setValidation("v_status")
    table.add (operation_id, operation_nm, status, data_quantity, organization_nm, login_nm, update_dt) // OPERATION_DATA
   
}

   
object ODO_OPR001  extends ODO_OPR001 {
	var body = Map(
        "title" -> title, 
        "table" -> table,
        "mode" -> mode,
		"ok_botton" -> 	ok_button)
}
*/

    def mkOdo(pageName: String, operation_id: Long){ 

      // package and import
	  val odo_contents:String= "package ni.edu.uca.colibri.odo\n\n" +
        "import ni.edu.uca.colibri.fw._\n" +
        "import ni.edu.uca.colibri.dao._\n\n" +
        "class ODO_" + pageName + " {\n"



/**
 *
 * 次の内容を出力する
    var title= DataType ("Operation",1) // PAGE=Operation
    var operation_id= DataType("nbsp",1) // OPERATION_DATA=operation_id PAGE_ELEMENTS=nbsp OPERATION_DATA=1
    var operation_nm= DataType ("Operation_Name", 1) // 
    var status= DataType ("Status", 1)
    var data_quantity= DataType ("Data_Quantity",1)
    var organization_nm= DataType ("Organization",1)
    var login_nm= DataType ("User",1)
    var update_dt= DataType ("DateTime",1)
    var mode= DataType ("Operation_Name",1)
    var ok_button= DataType ("ok_button", 1)

    var table = TableType ("table", 1)
*/ 
/*      def mkVarline(page_elements:List[DAO_PAGE_ELEMENTS_001]):String = { 


        var title = "var title"

        var varCount = 0
        var ret:String = ""
        page_elements.map(e => { 
            // page_elementsのdata_idよりoperation_dataを検索し、各データの
          var dao:String = DAO_OPERATION_DATA_001.findAll(
                By(DAO_OPERATION_DATA_001.id, e.data_id)) match { 
                  case (d:DAO_OPERATION_DATA_001)::Nil =>  
                    numToType (d.data_type.toInt, d.size.toInt) 
                  case _ => "ERROR DUPLICATE OPERATION_DATA"
                }

            ret = ret + "var element" + varCount + "=" +  dao + e.label_nm + "\"" + ")\n"
          varCount+=1
        } // page_elements.map
      ) // page_elements
          ret
      } // mkVarline*/

      def mkOneVarline(varNm:String, 
                         labelNm:String, 
                         typeNm:String,
                         operation_id:Long):String = {
        "\tvar "+ varNm + " = " + typeNm + "(\"" + labelNm + "\", " + operation_id.toString + ")\n"
      }

      def mkTableType(panel:List[DAO_PANEL_001]):String={ 
        panel.map(a => mkOneVarline("table_" + a.id.toString, 
                                  "table", "TableType", operation_id)).mkString
      }

	  val odo_file: String= pathprefix + "scala\\ni\\edu\\uca\\colibri\\odo\\ODO_"+ pageName +".scala"

      var varlines = mkOneVarline("title", pageName, "DataType", operation_id)

        /**
         * numToType
         */
        def numToType(s:Pair[Int,Int]):String = { 
          s match { 
            case (value:Int, size:Int) if (value > 0 && size > 0) =>
              value match { 
                case 1 =>{ 
                  if (size <= 16) "TinyType" 
                  else if (size <= 64) "SmallType"
                  else if (size <=256) "MidiumType"
                  else "LargeType(\""
                } // case 1
                case 2 => "NumberType"
                case _ => "ERROR TYPE"
              } // value match
            case _ => "ERROR numToType FORMAT"
          }
        } // numToType in mkVarline in mkOdo

      def applyDefvarLine(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
        mkOneVarline(DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString, // データ名称にパネルidとテーブルコンテンツ順を追加
                                                pageElm.label_nm,
                                                numToType(DAO_OPERATION_DATA_001.getDataType(pageElm)),
                                                operation_id)
      }


      varlines = varlines + pageEapply(applyDefvarLine, "")()
      varlines = varlines + mkTableType(panel)
      varlines = varlines + pageEapply(mkControlLines, "")()
      varlines = varlines + pageEapply(mkEmbed, "")()
      varlines = varlines + mkTableAdd()
//      varlines = varlines + pageEapply(mkTableList, "")
/*
 * 次の内容を出力する

    operation_id.getControl(1).setControl(4, 0, 0) //Control=PAGE_ELEMENTS
    operation_id.getControl(2).setControl(4, 0, 0)
    operation_id.getControl(3).setControl(4, 0, 0)
    operation_id.getControl(4).setControl(4, 0, 0)
    operation_id.getControl(5).setControl(4, 0, 0)
    operation_id.getControl(6).setControl(4, 0, 0)
 
 */
      def mkControlLines(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
        "\t" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + ".getControl(" + tC.mode.toString + ").setControl(" + pageElm.control_id + ",0,0)\n"
      }

/*
 *  以下の内容を出力する
    operation_id.embedItem = <OPR001:operation_id />
    operation_nm.embedItem= <OPR001:operation_nm />
    status.embedItem= <OPR001:status />
    data_quantity.embedItem= <OPR001:data_quantity />
    organization_nm.embedItem= <OPR001:organization />
    login_nm.embedItem= <OPR001:login_nm />
    update_dt.embedItem= <OPR001:date />
*/ 
      def mkEmbed(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
        "\t" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + ".embedItem = <" + pageName + ":" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + " />\n"
      }

/*
 * 次の内容を出力する 
    table.add (operation_id, operation_nm, status, data_quantity, organization_nm, login_nm, update_dt) // OPERATION_DATA
*/
      def mkTableAdd():String={ 
        pageElements.map(a => 
          a match { case (panel_i:DAO_PANEL_001, 
                        tableCs:(List[(DAO_TABLE_CONTENTS_001, List[DAO_PAGE_ELEMENTS_001])])) => "\ttable_" + panel_i.id.toString + ".add(" +
                          tableCs.map(b => 
                            b match { case (tableC:DAO_TABLE_CONTENTS_001, 
                                            pageEs:List[DAO_PAGE_ELEMENTS_001]) =>
                                 pageEs.map(pageE => 
                                   DAO_OPERATION_DATA_001.getDataName(pageE) + "_" + panel_i.id.toString + "_" + tableC.order.toString).mkString // データ名称にパネルidとテーブルコンテンツ順を追加
                                     case _ => "TYPE ERROR1"
                                   }).mkString(",")
                   case _ => "TYPE ERROR2"
                 } 
                         
                       ).mkString + ")\n"
      }
      
/*      def mkTableList(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):(Int,String)={ 
        ""
      }*/

      var panelIndex = 0
/*      varlines = varlines + panel.map(panela => { 
          DAO_TABLE_CONTENTS_001.findAll(By(
// 各パネルごとにテーブルができる
            DAO_TABLE_CONTENTS_001.panel_id, panela.id))
                .map(table_contents =>
                  mkVarline(DAO_PAGE_ELEMENTS_001.findAll(
                    By(DAO_PAGE_ELEMENTS_001.id, table_contents.element_id)))
                   .map(_.toString + "\n")).mkString("\n")
varlines = varlines + "var table" + panelIndex + " TableType(\"table" + panelIndex + ", " + ")\n"

      })*/
      
/*
 * 次の内容を出力する
object ODO_OPR001  extends ODO_OPR001 {
	var body = Map(
        "title" -> title, 
        "table" -> table,
        "mode" -> mode,
		"ok_botton" -> 	ok_button)
}*/
      def objectLines(page_nm: String):String={ 
        def mkElm:String ={ 
           pageEapply(mkObjectElm, "")()
        }

        def mkObjTable:String ={ 
           panel.map(a => ",\n\t\"table_" + a.id.toString  + "\" -> table_" + a.id.toString).mkString
        }

        "}\n\nobject ODO_" + page_nm + " extends ODO_" + page_nm + "{\n" +
        "\tvar body = Map(\n" +
        "\t\"title\" -> title" + mkElm + mkObjTable + ")\n}\n"
      }

      def mkObjectElm(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
        ",\n\t\"" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + "\" -> " + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString
      }

      var odo_contents2 = odo_contents  + varlines + objectLines(pageName)
	  mkFile(odo_file, odo_contents2)
    }
	



/**
 * 以下の内容を出力する。
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


class BL_ADM001 {
		
	object Log extends Logger
	var odo= ODO_OPR001
	var lang_id:Int= 3	
	var operation_id = "-1"
    var mode="1"
      operation_id = Session.operation_idVar.get.toString
    var list:List[TableType] = Nil
/**
 * Initializar by Kaoru Sato
 */ 
    def init():Unit = { 

    }

  /**
   * Implimir titulo by Kaoru Sato
   */ 
	def title = {
        {init()}
     	<H2>{odo.body("title").toLabel(lang_id)}</H2>     
	}

  /**
   * Implimir mensajes by Kaoru Sato
   */
	
	def table = {
		<table>		
        { odo.body("table") match { case t:TableType => t.mkTableTitle(lang_id) }}
        <lift:BL_ADM001.listOp>
        { odo.body("table") match { case t:TableType => t.mkTableEmbed()} }
        </lift:BL_ADM001.listOp>
        </table>
	}
	
	def nextBtn()={
		list.map(a => a match { 
            case (a:TableType) => {     
        		Log.info("CHECKED:"+a.elements(0).checked.toString)
        		if (a.elements(0).checked == true){
        			var id = a.elements(0).getValue.toString.toInt
        			Session.operation_idVar(id)
        			Log.info("Variable operation_id SESSION: "+Session.operation_idVar.toString)
			        Session.modeVar(mode.toInt)
					S.redirectTo("/AdminPage/ADM002ADD")
        		} // end if
		    } // end case (a:TableType)
        }) // end list.map
	}
	
	def exitBtn()={
		S.redirectTo("/AdminPage/ADM001")
	}
	
	def listOp(form: NodeSeq) = list.flatMap( d => applyForm(form, d))
	
	
    def applyForm (form: NodeSeq, d:TableType) = { 
		bind("OPR001", form,
			 "operation_id" -> d(0).toForm(mode.toInt),
			 "operation_nm" -> d(1).toForm(mode.toInt),
			 "status" -> d(2).toForm(mode.toInt),  
			 "data_quantity" -> d(3).toForm(mode.toInt),
			 "organization" -> d(4).toForm(mode.toInt), 
			 "login_nm" -> d(5).toForm(mode.toInt), 
			 "date" -> d(6).toForm(mode.toInt))
    }
	

/**
 * boton de transaction by Kaoru Sato
 */ 
    def transaction():NodeSeq ={
		<table>
		  <tr>
		      <td><ADM001:Next><button>Next</button></ADM001:Next></td>
		      <td><ADM001:Exit><button>Exit</button></ADM001:Exit></td>
		  </tr>
		</table>
    }
	
  /**
   * Mostrar pagina major by Frania
   */ 
	 def display(form: NodeSeq) ={
       { mode = form.toString 
         mkList
       }
     <lift:BL_ADM001.execute form="post" multipart="true">	
	       {table}  
	       {transaction}
     </lift:BL_ADM001.execute>
	 }


  /**
   * mkList
   * listを作るためメソッドとした
   */ 
    def mkList() ={ 
	 list = {
	      def convert(d:DataControl, o:TableType):TableType = { 
	     	  Log.debug("updateModifiedList  operation_nm:"+ o.elements(1).getValue.toString+" opr_id:"+ o.elements(0).getValue.toString +"checked"+ o.elements(0).checked)
    	  o.elements(0).setValue(d.id)
    	  o.elements(1).setValue(d.operation_nm)
          o.elements(2).setValue(d.status)
          o.elements(3).setValue(DAO_OPERATION_001.count_data_quantity(d.id)) 
          o.elements(4).setValue(d.organization_nm)
          o.elements(5).setValue(d.login_nm) //DAO_LOGIN_USER_001
          o.elements(6).setValue(d.update_dt)
          o
          }
      val search:List[DAO_OPERATION_001] = DAO_OPERATION_001.getOperationByStatus(Seq(0))

       search.map(a => convert(a, odo.body("table") match {case t:TableType => t.copy  }))
     }

    }// end mkList

  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
      def doBind(form: NodeSeq):NodeSeq = {
		    bind ("ADM001", form,	
                "Next" -> SHtml.submit("next", nextBtn),
                "Exit" -> SHtml.submit("exit", exitBtn))
	}	
		
    	doBind(form)

    }
}

*/ 
	def mkSnippet (pageName: String, operation_id: Long){
		val snippet_file: String= pathprefix + "scala\\ni\\edu\\uca\\colibri\\snippet\\BL_"+ pageName +".scala";
		val snippet_head:String= 
          "package ni.edu.uca.colibri.snippet\n\n" +
	      "import ni.edu.uca.colibri._ \n" +
	      "import model._ \n" +
	      "import net.liftweb._ \n" +
	      "import http._ \n" +
	      "import SHtml._ \n" +
	      "import S._ \n" +
	      "import js._ \n" +
	      "import JsCmds._ \n" +
	      "import mapper._ \n" +
	      "import common._ \n" +
	      "import util._  \n" +
          "import scala.xml.{NodeSeq, Text} \n" +
	      "import Helpers._ \n" +
          "import odo._\n" +
          "import fw._\n\n"

	  val classHead = 
          "class BL_"+ pageName +" { \n" +
	      "\tvar odo= ODO_" + pageName + "\n" +
	      "\tvar lang_id:Int= 1\n" +
	      "\tvar operation_id = \"" + operation_id.toString + "\"\n" +
          "\tvar mode=\"1\"\n" +
          panel.map("\tvar list_" + _.id.toString + ":List[TableType] = Nil\n").mkString +
          "\t//operation_id = Session.operation_idVar.get.toString\n\n" 
          
      val classTitleMethod =
          "\t/**\n" +
          "\t * title method generated by Generate class\n" +
          "\t */\n" +
          "\tdef title = {\n" +
          "\t\t<H2>{odo.body(\"title\").toLabel(lang_id, operation_id.toInt)}</H2>\n" +
          "\t}\n\n"

      def tableDetail(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
        "\t\t{odo.body(\"" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + "\").toTableElement(lang_id, operation_id.toInt)}\n"
      }

      def tableDetailMethod(ids:List[String]) = 
          ids.map(id =>
          "\t/**\n" +
          "\t * tableDetail method generated by Generate class\n" +
          "\t */\n" +
	      "\tdef tableDetail_" + id.toString + " = {\n" +
          "\t\t<table>\n" +
                  pageEapply(tableDetail, "")(id.toLong) +
          "\t\t</table>\n" +
          "\t}\n\n"
                ).mkString

      def tableMethod(ids:List[String]) =
          ids.map(id => 
          "\t/**\n" +
          "\t * table method generated by Generate class\n" +
          "\t */\n" +
	      "\tdef table_" + id + " = {\n" +
	      "\t\t<table>\n" +
          "\t\t{ odo.body(\"table_" + id + "\") match { case t:TableType => t.mkTableTitle(lang_id, operation_id.toInt) }}\n" +
          "\t\t<lift:BL_" + pageName + ".listOp_" + id + ">\n" +
          "\t\t{ odo.body(\"table_"+ id + "\") match { case t:TableType => t.mkTableEmbed()} }\n" +
          "\t\t</lift:BL_" + pageName + ".listOp_" + id + ">\n" +
          "\t\t</table>\n" +
          "\t}\n\n"
                ).mkString

        def listOpMethod(ids:List[String]) = {
          ids.map(id =>
          "\t/**\n" +
          "\t * listOp_" + id + " method generated by Generate class\n" +
          "\t */\n" +
	      "\tdef listOp_" + id + "(form: NodeSeq) = list_" +id+ ".flatMap( d => applyForm(form, d))\n\n"
                ).mkString
        }

        def mkBindLines (panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
          "\n\t\t\"" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + "\" -> d(" + tC.order.toString + ").toForm(mode.toInt)"
        }

        val applyFormMethod =
          "\t/**\n" +
          "\t * applyForm method generated by Generate class\n" +
          "\t */\n" +
          "\tdef applyForm (form: NodeSeq, d:TableType) = {\n" +
          "\t\tbind(\"" + pageName + "\", form, " + pageEapply(mkBindLines, ", ")() + ")\n\t}\n\n"

        val transactionMethod =
          "\t/**\n" +
          "\t * transaction method generated by Generate class\n" +
          "\t */\n" +
          "\tdef transaction():NodeSeq ={\n" +
	      "\t\t<table>\n" +
		  "\t\t  <tr>\n" +
	      "\t\t    <td><" + pageName + ":Next><button>Next</button></" + pageName + ":Next></td>\n" +
	      "\t\t    <td><" + pageName + ":Exit><button>Exit</button></" + pageName + ":Exit></td>\n" +
		  "\t\t  </tr>\n" +
	      "\t\t</table>\n" +
          "\t}\n\n"

        val displayMethod =
          "\t/**\n" +
          "\t * display method generated by Generate class\n" +
          "\t */\n" +
	      "\tdef display(form: NodeSeq) ={\n" +
          "\t\t{ mode = form.toString \n" +
          panel.map(a => "\t\t\tmkList_" + a.id.toString + "\n").mkString +
          "\t\t}\n" +
          "\t\t<lift:BL_" + pageName + ".execute form=\"post\" multipart=\"true\">	\n" +
	      panel.map(a => "\t\t\t{tableDetail_" + a.id.toString + "}  \n").mkString +
	      panel.map(a => "\t\t\t{table_" + a.id.toString + "}  \n").mkString +
	      "\t\t\t{transaction}\n" +
          "\t\t</lift:BL_" + pageName + ".execute>\n" +
	      "\t}\n\n"

        def mkLabelNames (panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
          "\"" + pageElm.label_nm + "\""
        }

        def mkListMethod(ids:List[String]) = { 
          ids.map(id =>
          "\t/**\n" +
          "\t * mkList method generated by Generate class\n" +
          "\t */\n" +
          "\tdef mkList_" + id + "() ={\n" +
          "\t\tval label_nms = List(" + pageEapply(mkLabelNames, ", ")() + ")\n" +
	      "\t\tlist_" + id + " = DataControl(" + operation_id.toString + ",label_nms)\n" +
	      "\t\t\t//def convert(d:DataControl, o:TableType):TableType = { \n" +
          "\t\t\t\t//o.elements(0).setValue(d.id)\n" +
          "\t\t\t\t//o\n" +
          "\t\t\t//}\n" +
          "\t\t\t//val search:List["+"] = " + "\n" +
          "\t\t\t//search.map(a => convert(a, odo.body(\"table\") match {case t:TableType => t.copy  }))\n" +
          "\t\t//}\n" +
          "\t}// end mkList\n\n").mkString
        }

        def bindTable(panelId:DAO_PANEL_001,
                   pageElm:DAO_PAGE_ELEMENTS_001, 
                   tC:DAO_TABLE_CONTENTS_001):String={ 
          "\t\t\t\t\"" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + "\" -> odo.body(\"" + DAO_OPERATION_DATA_001.getDataName(pageElm) + "_" + panelId.id.toString + "_" + tC.order.toString + "\").toForm(mode.toInt), \n"
        }
        val executeMethod =
          "\t/**\n" +
          "\t * execute method generated by Generate class\n" +
          "\t */\n" +
          "\tdef execute(form: NodeSeq):NodeSeq ={\n" +
          "\t\tdef doBind(form: NodeSeq):NodeSeq = {\n" +
          "\t\t\tbind (\"" + pageName + "\", form,\n"+ 
          pageEapply(bindTable, "")() + 
          "\t\t\t\t\"Next\" -> SHtml.submit(\"next\", nextBtn),\n" +
          "\t\t\t\t\"Exit\" -> SHtml.submit(\"exit\", exitBtn))\n" +
          "\t\t}\n" +
          "\t\tdoBind(form)\n" +
          "\t}\n"

        val nextBtnMethod = 
	      "\tdef nextBtn()={\n" +
	      "\t\tS.redirectTo(\"/AdminPage/ADM001\")\n" +
          "\t}\n\n"

        val exitBtnMethod = 
	      "\tdef exitBtn()={\n" +
	      "\t\tS.redirectTo(\"/AdminPage/ADM001\")\n" +
          "\t}\n\n"

        val panelids = panel.map(_.id.toString) 
        val snippet_contents = snippet_head + classHead + 
            classTitleMethod + tableDetailMethod(panelids) + tableMethod(panelids) + listOpMethod(panelids) +
            applyFormMethod + transactionMethod + displayMethod + 
            mkListMethod(panelids) + executeMethod + nextBtnMethod + 
            exitBtnMethod + "}\n"

		mkFile(snippet_file, snippet_contents )
	}
	
  mkAll(page_id)

}
