package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_MIDIUM_TYPE_001 extends LongKeyedMapper[DAO_MIDIUM_TYPE_001] with IdPK {


	def getSingleton = DAO_MIDIUM_TYPE_001
//	def primaryKeyField = data_id	
    object data_id extends MappedLongForeignKey(this, DAO_DATA_CONTROL_001)
	object value extends MappedString(this, 256) 
	object order extends MappedInt(this)	 
}

object DAO_MIDIUM_TYPE_001 extends DAO_MIDIUM_TYPE_001 with LongKeyedMetaMapper[DAO_MIDIUM_TYPE_001]{

   override def dbTableName = "MIDIUM_TYPE"
   override def fieldOrder = List(data_id, value, order)
/*   getLabel(order:, value:):String = {
	   "test"
   }*/
}
