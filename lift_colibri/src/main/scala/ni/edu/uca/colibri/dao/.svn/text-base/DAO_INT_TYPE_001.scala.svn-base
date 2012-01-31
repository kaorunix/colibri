package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_INT_TYPE_001 extends LongKeyedMapper[DAO_INT_TYPE_001] with IdPK {


	def getSingleton = DAO_INT_TYPE_001
//	def primaryKeyField = label_id	
    object data_id extends MappedLong(this)
	object value extends MappedInt(this) 
	object order extends MappedInt(this)	 
}

object DAO_INT_TYPE_001 extends DAO_INT_TYPE_001 with LongKeyedMetaMapper[DAO_INT_TYPE_001]{

   override def dbTableName = "INT_TYPE"
   override def fieldOrder = List(data_id, value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}
