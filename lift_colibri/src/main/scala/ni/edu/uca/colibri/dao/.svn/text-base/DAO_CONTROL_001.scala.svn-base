package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_CONTROL_001 extends LongKeyedMapper[DAO_CONTROL_001] with IdPK
{
	def getSingleton = DAO_CONTROL_001
//	def primaryKeyField = control_id
	object control_id extends MappedLong(this)
	object mode extends MappedInt(this)
}

object DAO_CONTROL_001 extends DAO_CONTROL_001 with LongKeyedMetaMapper[DAO_CONTROL_001]{

   override def dbTableName = "CONTROL"
   override def fieldOrder = List(control_id, mode)
/*   getLabel(mode:):String = {
	   "test"
   }*/
}
