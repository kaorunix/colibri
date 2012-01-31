package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.Logger

class DAO_MESSAGE_001 extends LongKeyedMapper[DAO_MESSAGE_001] with IdPK {
	object Log extends Logger

	def getSingleton = DAO_MESSAGE_001
//	def primaryKeyField = message_id
	object message_nm extends MappedString(this, 16)
	object lang_id extends MappedLongForeignKey(this, DAO_LANGUAGE_001)
	object message extends MappedString(this, 1024) 
	object operation_id extends MappedLong(this) 
}

object DAO_MESSAGE_001 extends DAO_MESSAGE_001 with LongKeyedMetaMapper[DAO_MESSAGE_001]{

   override def dbTableName = "MESSAGE"
   override def fieldOrder = List(message_nm, lang_id, message, operation_id)
    
   def getMessage(lang_id:Int, message_nm: String, operation_id:Long):String = {
      Log.info("DAO_MESSAGE_001: lang_id: " + lang_id + " message_nm: " + message_nm + " operation_id: " + operation_id)
	   val query = DAO_MESSAGE_001.findAll(BySql("lang_id=? and  message_nm=? and operation_id=?", IHaveValidatedThisSQL("dchenbecker", 
                              "2008-12-03"), lang_id, message_nm, operation_id))
                              
      Log.info("DAO_MESSAGE_001.query:" + query.toString)
      query match { 
        case (a:DAO_MESSAGE_001)::Nil => a.message.toString
        case _ => "ERROR de MESSAGE:"+ message_nm
      }
   }
}
  
  
   
