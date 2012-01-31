package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_DATA_TYPE_001 extends LongKeyedMapper[DAO_DATA_TYPE_001] with IdPK {


	def getSingleton = DAO_DATA_TYPE_001
//	def primaryKeyField = data_id	
	object type_nm extends MappedString(this, 16) 
	object order extends MappedInt(this)	 
}

object DAO_DATA_TYPE_001 extends DAO_DATA_TYPE_001 with LongKeyedMetaMapper[DAO_DATA_TYPE_001]{

   override def dbTableName = "DATA_TYPE"
   override def fieldOrder = List(type_nm, order)
}
