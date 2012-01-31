package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_LANGUAGE_001 extends LongKeyedMapper[DAO_LANGUAGE_001] with IdPK {
//class DAO_LANGUAGEL_001 extends KeyedMapper[Long, DAO_LANGUAGEL_001] {

	def getSingleton = DAO_LANGUAGE_001
	object lang_id extends MappedLong (this)
	object lang_nm extends MappedString (this, 16)
}


object DAO_LANGUAGE_001 extends DAO_LANGUAGE_001 with LongKeyedMetaMapper[DAO_LANGUAGE_001]{
//object DAO_LOGIN_001 extends DAO_LOGIN_001 with KeyedMetaMapper[Long,DAO_LOGIN_001]{
   override def dbTableName = "LANGUAGE"
   override def fieldOrder = List(lang_id, lang_nm)
   
}