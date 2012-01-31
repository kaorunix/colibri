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
import java.util._
import _root_.ni.edu.uca.colibri.ido._
import _root_.ni.edu.uca.colibri.odo._
import _root_.ni.edu.uca.colibri.fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._

class BL_ADM005a {
	
	object Log extends Logger
	var odo= ODO_ADM004
	var lang_id:Int= 1	
	var operation_id = "-1"
	var page_id= "-1"
	var page_tp= "0"
    var mode="0"
    operation_id = Session.operation_idVar.get.toString
    page_id = Session.page_idVar.get.toString

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
	def head = {
        <div>Page para probar ADM005 o ADM006</div>
		<table>
          <tr><th>Operation_id</th><td><ADM005a:operation_id /></td></tr>
          <tr><th>Page_id</th><td><ADM005a:page_id /></td></tr>
          <tr><th>mode</th><td><ADM005a:mode /></td></tr>
          <tr><th>page_tp</th><td><ADM005a:page_tp /></td></tr>
		</table>
	}

/**
 * boton de transaction by Kaoru Sato
 */ 
    def transaction():NodeSeq ={
		<table>
		  <tr>
		      <td><ADM005a:submit><button>OK</button></ADM005a:submit></td>
		  </tr>
		</table>
    }
	
  /**
   * Mostrar pagina major by Frania
   */ 
	 def display(form: NodeSeq) ={
     <lift:BL_ADM005a.execute form="post" multipart="true">	
	       {head}
	       {transaction}
     </lift:BL_ADM005a.execute>
	 }
	
  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
        def redirectADM4() = {
            Session.operation_idVar(operation_id.toInt)
            Session.page_idVar(page_id.toInt)
            Session.modeVar(mode.toInt)
            page_tp match {
              case "1" => S.redirectTo("/AdminPage/ADM005")
              case "2" => S.redirectTo("/AdminPage/ADM006")
    
            }
            
        }
    
    	def doBind(form: NodeSeq):NodeSeq = {
		    bind ("ADM005a", form,	
                "operation_id" -> SHtml.text("", operation_id = _),
                "page_id" -> SHtml.text("",page_id= _),
                "mode" -> SHtml.text("", mode = _),
                "page_tp" -> SHtml.text("", page_tp = _),
		    	"submit" -> SHtml.submit("OK", redirectADM4))
    		}	
		
    	doBind(form)

    }

}
