package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_DATA_CONTROL_001 extends LongKeyedMapper[DAO_DATA_CONTROL_001] with IdPK {


	def getSingleton = DAO_DATA_CONTROL_001
//	def primaryKeyField = data_id	
	object operation_id extends MappedLong(this) 
//	object data_id extends MappedLong(this)	 
}

object DAO_DATA_CONTROL_001 extends DAO_DATA_CONTROL_001 with LongKeyedMetaMapper[DAO_DATA_CONTROL_001]{

   override def dbTableName = "DATA_CONTROL"
   override def fieldOrder = List(operation_id)
}
