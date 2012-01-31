package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_NUMERIC_TYPE_001 extends LongKeyedMapper[DAO_NUMERIC_TYPE_001] with IdPK {


	def getSingleton = DAO_NUMERIC_TYPE_001
//	def primaryKeyField = label_id	
    object data_id extends MappedLong(this)
	object value extends MappedDouble(this) 
	object order extends MappedInt(this)	 
}

object DAO_NUMERIC_TYPE_001 extends DAO_NUMERIC_TYPE_001 with LongKeyedMetaMapper[DAO_NUMERIC_TYPE_001]{

   override def dbTableName = "NUMERIC_TYPE"
   override def fieldOrder = List(value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}
