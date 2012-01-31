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
import java.util._
import ido._
import odo._
import fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._


class BL_INF001 {
		
	object Log extends Logger
	var odo= ODO_INF001
	var lang_id:Int= 1	
	var operation_id = -1
      operation_id = Session.operation_idVar.get.toInt  

/**
 * Initializar by Kaoru Sato
 */ 
    def init():Unit = { 
      Log.info("BL_INF001:init")
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
      Log.info("INF001:head:")
		<table>
		{odo.body("message").toMessage(lang_id)}
		</table>
	}

/**
 * boton de transaction by Kaoru Sato
 */ 
    def transaction():NodeSeq ={
      Log.info("INF001:transaction:")
		<table>
		  <tr>
		      <td><INF001:submit><button>OK</button></INF001:submit></td>
		  </tr>
		</table>
    }
	
  /**
   * Mostrar pagina major by Frania
   */ 
	 def display(form: NodeSeq) ={
     <lift:BL_INF001.execute form="post" multipart="true">	
	       {head}
	       {transaction}
     </lift:BL_INF001.execute>
	 }
	
  /**
   * ejecutar procedimiento by Frania
   */
    def execute(form: NodeSeq):NodeSeq ={
        def next() ={ 
            //redirectTo("/Operation/OPR001REF")
            true
        }
    	def doBind(form: NodeSeq):NodeSeq = {
		    bind ("INF001", form,	
		    	"submit" -> SHtml.submit("OK", next))
    		}	
		
    	doBind(form)

    }
}
