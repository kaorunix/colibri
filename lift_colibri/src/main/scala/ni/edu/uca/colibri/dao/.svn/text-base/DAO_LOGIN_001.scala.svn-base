package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_LOGIN_001 extends LongKeyedMapper[DAO_LOGIN_001] with IdPK {

	def getSingleton = DAO_LOGIN_001
//	def primaryKeyField = login_id
	object login_id extends MappedLongForeignKey(this, DAO_LOGIN_USER_001)//IndexedField(this) //Index(this)//)
	object cookie extends MappedString(this, 16)
	object login_dt extends MappedDateTime(this) 
}

object DAO_LOGIN_001 extends DAO_LOGIN_001 with LongKeyedMetaMapper[DAO_LOGIN_001]{
   override def dbTableName = "LOGIN"
   override def fieldOrder = List(login_id, cookie, login_dt)
}
