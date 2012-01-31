package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_DATE_TYPE_001 extends LongKeyedMapper[DAO_DATE_TYPE_001] with IdPK {


	def getSingleton = DAO_DATE_TYPE_001
//	def primaryKeyField = label_id	
    object data_id extends MappedLongForeignKey(this, DAO_DATA_CONTROL_001)
	object value extends MappedDateTime(this) 
	object order extends MappedInt(this)	 
}

object DAO_DATE_TYPE_001 extends DAO_DATE_TYPE_001 with LongKeyedMetaMapper[DAO_DATE_TYPE_001]{

   override def dbTableName = "DATE_TYPE"
   override def fieldOrder = List(value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}
