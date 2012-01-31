package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._

class DAO_LOGIN_USER_001 extends LongKeyedMapper[DAO_LOGIN_USER_001] with IdPK {
    object Log extends Logger

	def getSingleton = DAO_LOGIN_USER_001
//	def primaryKeyField = login_id
	object login_id extends MappedLong(this)
	object login_nm extends MappedString(this, 16)
	object organization_nm extends MappedString(this, 16) //ForeignKey(DAO_ORGANIZATION_001)
	object logout_dt extends MappedDateTime(this)
	object password extends MappedString(this, 16) {
	//	override def validations = validPriority _ :: super.validations

	/*	def validPriority(in: String): List[FieldError] =
			if (in == "password") Nil
			else List(FieldError(this, <b>Contraseña es incorrectra</b>))
	*/}
	object lang_id extends MappedLong(this)
	object date_format extends MappedLong(this)
   def getRecord(orgNm: String, loginNm:String): DAO_LOGIN_USER_001 = {
       Log.info("orgNm: "+orgNm + " loginNm: "+loginNm)
//	   DAO_LOGIN_USER_001.findAll(BySql("LOGIN_USER.organization_nm  = ? and LOGIN_USER.login_nm = ?, '" 
//	   			+ orgNm + "','" + loginNm + "'", 
	   DAO_LOGIN_USER_001.findAll(BySql("LOGIN_USER.organization_nm  = ? and LOGIN_USER.login_nm = ?",
                                        IHaveValidatedThisSQL("kaoru", "2011-03-03"),
                                        orgNm, loginNm))(0)
   }
}

object DAO_LOGIN_USER_001 extends DAO_LOGIN_USER_001 with LongKeyedMetaMapper[DAO_LOGIN_USER_001]{
   override def dbTableName = "LOGIN_USER"
   override def fieldOrder = 
	   List(login_id, login_nm, organization_nm, logout_dt, password, lang_id, date_format)
   def login = {
	   val login_info = DAO_LOGIN_001.create.login_id(this.login_id)
	   login_info.save
   }

}
