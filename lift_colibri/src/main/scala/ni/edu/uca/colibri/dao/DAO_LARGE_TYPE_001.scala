package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_LARGE_TYPE_001 extends LongKeyedMapper[DAO_LARGE_TYPE_001] with IdPK {


	def getSingleton = DAO_LARGE_TYPE_001
//	def primaryKeyField = data_id	
    object data_id extends MappedLong(this)
	object value extends MappedString(this, 1024) 
	object order extends MappedInt(this)	 
}

object DAO_LARGE_TYPE_001 extends DAO_LARGE_TYPE_001 with LongKeyedMetaMapper[DAO_LARGE_TYPE_001]{

   override def dbTableName = "LARGE_TYPE"
   override def fieldOrder = List(value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}
