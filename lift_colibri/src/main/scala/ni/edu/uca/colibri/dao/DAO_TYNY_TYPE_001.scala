package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_TYNY_TYPE_001 extends LongKeyedMapper[DAO_TYNY_TYPE_001] with IdPK {


	def getSingleton = DAO_TYNY_TYPE_001
//	def primaryKeyField = data_id	
	object value extends MappedString(this, 16) 
	object order extends MappedInt(this)	 
}

object DAO_TYNY_TYPE_001 extends DAO_TYNY_TYPE_001 with LongKeyedMetaMapper[DAO_TYNY_TYPE_001]{

   override def dbTableName = "TYNY_TYPE"
   override def fieldOrder = List(value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}