package ni.edu.uca.colibri.snippet
import java.sql._
import java.sql.{Date=>SDate}
import javax.swing.JOptionPane
import ni.edu.uca.colibri.dao._
import net.liftweb.common.Logger
import java.util._
import java.util.{Date=>UDate}
import java.lang.Math._

class User {
	var login_id: Long =0
	var login_nm : String=""
	var org_nm : String=""
	var lang_id : Int = 0
	var date_format: Long = 0
/*	var firstname : String=null
	var middlename : String=null
	var lastname : String=null
	var typeid : String=null
	var nationality : String=null
	var email : String=null
	var phonenumber : String=null
	var password : String=null
	var cn : Conexion=null
	var pstm : PreparedStatement=null
	var rs : ResultSet=null
	var sql : String=null*/
	//var usr: DAO_LOGIN_USER_001 //.create
	val logout = DAO_LOGIN_USER_001
	var usr = DAO_LOGIN_USER_001
	
	object Log extends Logger
	def login(org:String, username:String, password:String):Option[User]={
        var usr = new DAO_LOGIN_USER_001
		usr.getRecord(org,username)
	    login_id = usr.login_id.toLong
	    login_nm = usr.login_nm
	    org_nm = usr.organization_nm
	    lang_id =usr.login_id.toInt
	  date_format = usr.date_format.toLong
      
		usr.password match {
			case password if (saveLogin)=> Some(this)
			case password if (!saveLogin) => None
			case _ => None
		}
	}
	
	def saveLogin: Boolean ={
	  val login = DAO_LOGIN_001
		DAO_LOGIN_001.create
	      .login_id(usr.login_id)
	      .cookie("abcdefghi")
	      .login_dt(new UDate)
	      .save
	}
	
/*	def getUser(): Unit = {
		this.login_id = usr.login_id
		this.login_nm = usr.login_nm
		this.org_nm = usr.organization_nm
		this.lang_id = usr.lang_id
		this.date_format = usr.date_format

	}*/
			
	
	def savelogout(): Boolean = {
		DAO_LOGIN_001.create
		logout.login_id(this.login_id)
		logout.login_nm(this.login_nm)
		logout.logout_dt(new UDate)
		logout.lang_id(this.lang_id)
		logout.organization_nm(this.org_nm)
		logout.date_format(this.date_format)
		logout.save
	}
		
	
	def logout(login_id:Int): Option[User] = {
		usr.login_id match{
			case login_id if(savelogout) => clear; Some(this)
			case login_id if(!savelogout) => None; 
			case _ => None
		}
	}
	
	def clear(): Unit = {
		this.login_id = Integer.parseInt(null)
		this.login_nm = null
		this.org_nm = null
		this.lang_id = Integer.parseInt(null)
		this.date_format = Integer.parseInt(null)
	}
}
