package ni.edu.uca.colibri.dao
import _root_.net.liftweb.common.Logger
import _root_.net.liftweb.mapper._

class DAO_SYSTEM_VALIDATION_001 extends LongKeyedMapper[DAO_SYSTEM_VALIDATION_001] with IdPK {
//class DAO_LABEL_001 extends KeyedMapper[Long, DAO_LABEL_001] {

	def getSingleton = DAO_SYSTEM_VALIDATION_001
//	def primaryKeyField = label_id
	object validation_nm extends MappedString(this, 16)
	object value extends MappedInt(this)
	object label_nm extends MappedString(this, 16) 
	object explain extends MappedString(this, 256) 
	
	}

object DAO_SYSTEM_VALIDATION_001 extends DAO_SYSTEM_VALIDATION_001 with LongKeyedMetaMapper[DAO_SYSTEM_VALIDATION_001]{
//object DAO_LOGIN_001 extends DAO_LOGIN_001 with KeyedMetaMapper[Long,DAO_LOGIN_001]{
   override def dbTableName = "SYSTEM_VALIDATION"
   override def fieldOrder = List(validation_nm, value, label_nm, explain)
   
  def toLabel(val_nm:String, value:Int, lang_id: Int):String ={
	   val lbn= DAO_SYSTEM_VALIDATION_001.findAll(By(DAO_SYSTEM_VALIDATION_001.validation_nm, val_nm),
	  		    By(DAO_SYSTEM_VALIDATION_001.value, value))
	   DAO_LABEL_001.getLabel(lang_id, lbn match {
	  	   case (a:DAO_SYSTEM_VALIDATION_001)::Nil => a.label_nm
	  	   case _ => "ERROR No existe validation"
	   }, 1)
   }
   
   def getSelectList(val_nm:String)= { 
       val result = DAO_SYSTEM_VALIDATION_001.findAll(By(DAO_SYSTEM_VALIDATION_001.validation_nm, val_nm))
       result.map(a => (a.value.toString, a.label_nm.toString))
   }
}
