package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.Logger
import _root_.net.liftweb.http._

class DAO_TABLE_CONTENTS_001 extends LongKeyedMapper[DAO_TABLE_CONTENTS_001] with IdPK {
	object Log extends Logger

	def getSingleton = DAO_TABLE_CONTENTS_001
//	def primaryKeyField = label_id
	object panel_id extends MappedLong(this)
	object element_id extends MappedLong(this)
	object mode extends MappedInt(this)
//	object actual extends MappedInt(this) 
	object order extends MappedLong(this) 
}

object DAO_TABLE_CONTENTS_001 extends DAO_TABLE_CONTENTS_001 with LongKeyedMetaMapper[DAO_TABLE_CONTENTS_001]{

  override def dbTableName = "TABLE_CONTENTS"
  override def fieldOrder = List(panel_id, element_id, mode, order)
//element_id:Long
  def saveTableContentsWithValue(panel_id:Long, mode:Int, order: Long, contents_id:Long)={
        DAO_TABLE_CONTENTS_001.findAll(
            By(DAO_TABLE_CONTENTS_001.panel_id, panel_id),
            By(DAO_TABLE_CONTENTS_001.id, contents_id))
        match { 
          case (a:DAO_TABLE_CONTENTS_001)::Nil => { 
    Log.debug("saveTableContentsWithValue panel_id:" + panel_id+ " element_id:"+ a.element_id.toString + "  order:"+ order)
//                a.element_id(element_id)
                 a.mode(mode)
                 .order(order)
                 .save
            }
          case _ => S.error("INVALID DATA in TABLE_CONTENTS panel_id=" + panel_id.toString + " contents_id=" + contents_id)
     
        }
    }

    def addTableContents(panel_id:Long, element_id:Long, mode:Int)={ 
      val torder = (DAO_TABLE_CONTENTS_001.findAll(By(DAO_TABLE_CONTENTS_001.panel_id, panel_id))).foldLeft(-1)(_ max _.order.toInt) + 1
      DAO_TABLE_CONTENTS_001.create
          .panel_id(panel_id)
          .element_id(element_id)
          .mode(mode)
          .order(torder)
          .save
    }
      
   def delTableContents(panel_id:Long, contents_ids:Seq[Long])={
       DAO_TABLE_CONTENTS_001.findAll(
             ByList(DAO_TABLE_CONTENTS_001.id, contents_ids),
             By(DAO_TABLE_CONTENTS_001.panel_id, panel_id))
           .map(_.delete_!)
   }

}
  
  
   
