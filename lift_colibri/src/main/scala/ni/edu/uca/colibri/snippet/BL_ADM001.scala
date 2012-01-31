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
	      def convert(d:DAO_OPERATION_001, o:TableType):TableType = { 
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
/*{
        Log.info("BL_OPR001:search:mode case "+ mode)
             mode match {
                // Ref
                case "1" =>DAO_OPERATION_001.getOperationByStatus(Seq(0,1,2,3))	
                // Add
                case "2" => {	Session.operation_idVar(0)
                				Session.modeVar(mode.toInt)
                				S.redirectTo("/Operation/OPR002ADD")  
                }
                // Del
                case "4" => { 
                  Log.info("BL_OPR001:search:case 4")
                  DAO_OPERATION_001.findAll(By(DAO_OPERATION_001.status, 0), By(DAO_OPERATION_001.delete_fg, 0)) }
                // Undo App
                case "3"|"5"|"6" => DAO_OPERATION_001.getOperationByStatus(Seq(0,1,2))
              }
            }*/
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
