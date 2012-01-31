package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._

class DAO_PANEL_001 extends LongKeyedMapper[DAO_PANEL_001] with IdPK
{
	def getSingleton = DAO_PANEL_001
//	def primaryKeyField = control_id
	object page_id extends MappedLongForeignKey(this, DAO_PAGE_001)
//	object table_id extends MappedLong(this)
//	object sentence_id extends MappedLong(this)
//	object transaction_id extends MappedLong(this)
//	object actual extends MappedInt(this)
	object order extends MappedLong(this)
}

object DAO_PANEL_001 extends DAO_PANEL_001 with LongKeyedMetaMapper[DAO_PANEL_001]{

   override def dbTableName = "PANEL"
   override def fieldOrder = List(page_id, order)
/*   getLabel(mode:):String = {
	   "test"
   }*/
/*   def savePanelWithValue(page_id:Int, order: Long, panel_id: Long, actualOld:Int, actualNew:Int)={
        DAO_PAGE_ELEMENTS_001.findAll(
            By(DAO_PAGE_ELEMENTS_001.page_id, page_id),
            By(DAO_PAGE_ELEMENTS_001.order, order),
            By(DAO_PAGE_ELEMENTS_001.id, panel_id),
            By(DAO_PAGE_ELEMENTS_001.actual, actualOld))
        match { 
            case (a:DAO_PAGE_ELEMENTS_001)::Nil => { 
    Log.debug("savePageElementsWithValue page_id:" + page_id+ " data_nm:"+ a.data_id.toString + "->" + data_id+ "  order:"+ order+"MAX "+maxlength.toString)
                a.label_nm(label_nm)
                 .data_id(data_id)
                 .control_id(control_id)
                 .size(size)
                 .maxlength(maxlength)
                 .note(note)
                 .order(order)
                 .actual(actualNew)
                 .save
            }
            case Nil => DAO_PAGE_ELEMENTS_001.create
                  .page_id(page_id)
                  .data_id(data_id)
                  .control_id(control_id)
                  .size(size)
                  .maxlength(maxlength)
                  .note(note)
                  .order(order)
                  .actual(actualNew)
                  .save
         case _ => S.error("INVALID DATA in PAGE_ELEMENTS page_id=" + page_id.toString)
     
        }
    }*/
}
