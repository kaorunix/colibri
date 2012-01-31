package ni.edu.uca.colibri.snippet 

import _root_.scala.xml.{NodeSeq, Text}
import _root_.net.liftweb.util._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http.SHtml
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._
import ni.edu.uca.colibri.dao._
import ni.edu.uca.colibri.fw._
import Helpers._
import ni.edu.uca.colibri.ido._
import ni.edu.uca.colibri.odo._

class BL_LOG001 {
    object Log extends Logger

    var lang_id = 1 // English
    var ido = IDO_LOG001
    var odo = ODO_LOG001
    val usr = new User
    
    def init { 
        usr.login("uca","user1","password")
      Log.info("init1:lang_id:" + usr.lang_id)
        lang_id = usr.lang_id
      Log.info("init2:lang_id:" + lang_id)
    }


    def title = {
//        if (!initialize) init
//      init
      Log.info("title:init end:"  )
        <H2>{odo.body("title").toLabel(lang_id)}</H2>
//        <spam>{usr.login_nm.toString}</spam>
    }
      
    def login() = { 
    <table>
      {odo.body("organization_nm").toTableElement(lang_id)}
      {odo.body("login_nm").toTableElement(lang_id)}
      {odo.body("password").toTableElement(lang_id)}
    </table>
    }

//odo.body("longin_nm").toForm

    def transaction() = {
      <table>
        <tr>
	      <td><LOG001_1:submit><button>Login</button></LOG001_1:submit></td>
        </tr>
      </table>
    }

    def display(form: NodeSeq) = { 
  <lift:BL_LOG001.execute form="post" multipart="true">
        {login}
        {transaction}
  </lift:BL_LOG001.execute>
    }

	def execute(form: NodeSeq) = {
		val input_usr = DAO_LOGIN_USER_001.create

		def checkAndLogin(): Unit = {
			Log.info("checkAndLogin:begin:organization_nm = " + input_usr.organization_nm)
			ido.body("organization_nm").setValue(input_usr.organization_nm)
			ido.body("login_nm").setValue(input_usr.login_nm)
			ido.body("password").setValue(input_usr.password)

            usr.login(ido.body("organization_nm").getValue.toString,
                      ido.body("login_nm").getValue.toString,
                      ido.body("password").getValue.toString)
				//login.save; S.notice("Logined " + input_usr.login_nm)
			
//			} else {
//				S.error(<spam>error</spam>); S.mapSnippet("Login", doBind)
//			}
		}
//ido.body("organization_nm").toForm,		
		def doBind(form: NodeSeq) = {
/*			val rule = Map(
				"organization_nm" -> input_usr.organization_nm.toForm,
				"login_nm" -> input_usr.login_nm.toForm,
				"password" -> input_usr.password.toForm)
			Log.error("LOGTEST");
            bind(rule, form)*/
			bind("LOG001_1", form, //rule)
				"organization_nm" -> input_usr.organization_nm.toForm,
				"login_nm" -> input_usr.login_nm.toForm,
				"password" -> input_usr.password.toForm,
//				"submit" -> input_usr.toForm(Full("login"), "/Admin/ADM001"))
				"submit" -> SHtml.submit("Login", checkAndLogin))
		}
		doBind(form)
	}




}

