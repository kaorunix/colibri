package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_LAYOUT_001 extends LongKeyedMapper[DAO_LAYOUT_001] with IdPK
{
	def getSingleton = DAO_LAYOUT_001
//	def primaryKeyField = control_id
	object layout_id extends MappedLong(this)
	object panel_id extends MappedLong(this)
}

object DAO_LAYOUT_001 extends DAO_LAYOUT_001 with LongKeyedMetaMapper[DAO_LAYOUT_001]{

   override def dbTableName = "LAYOUT"
   override def fieldOrder = List(layout_id, panel_id)
/*   getLabel(mode:):String = {
	   "test"
   }*/
}
