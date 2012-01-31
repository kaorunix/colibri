
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


class BL_ADM002a {
	
	object Log extends Logger
	var odo = ODO_ADM001
	var lang_id: Int = 1
	var operation_id = "1"
	var mode = "1"
		
	def title = {
		<h2>{odo.body("title").toLabel(lang_id)}</h2>
	}
	def head = {
		<table>
			<tr><th>Operation_id</th><td><ADM002:operation_id /></td></tr>
			<tr><th>Mode</th><td><ADM002:mode /></td></tr>
		</table>
	}
	def transaction(): NodeSeq = {
		<table>
			<tr><td><ADM002:add><button>OK</button></ADM002:add></td></tr>
		</table>
	}
	def display(form: NodeSeq) = {
		<lift:BL_ADM002a.execute form = "post" multipart = "true">
		{head}
		{transaction}
		</lift:BL_ADM002a.execute>
	}
	def execute(form: NodeSeq) = {
		def redirectADM2() = {
			Session.operation_idVar(operation_id.toInt)
			Session.modeVar(mode.toInt)
			Log.info("REDIRIGIENDO EXITOSAMENTE")
			S.redirectTo("/AdminPage/ADM002ADD")
		}
		def doBind(form: NodeSeq) = {
			bind("ADM002", form, 
					"operation_id" -> SHtml.text("", operation_id =_),
					"mode" -> SHtml.text("", mode =_),
					"add" -> SHtml.submit("OK", redirectADM2))
		}
		doBind(form)
	}

}