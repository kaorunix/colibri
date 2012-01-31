package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.Logger
import _root_.ni.edu.uca.colibri.fw._
import _root_.net.liftweb.http._

class DAO_PAGE_ELEMENTS_001 extends LongKeyedMapper[DAO_PAGE_ELEMENTS_001] with IdPK  {
	object Log extends Logger
	
	def getSingleton = DAO_PAGE_ELEMENTS_001
	object page_id extends MappedLongForeignKey (this, DAO_PAGE_001)
	object data_id extends MappedLongForeignKey (this, DAO_OPERATION_DATA_001)	
	object label_nm extends MappedString(this, 16)
	object control_id extends MappedLong(this) //ForeignKey (this,DAO_CONTROL_001)
	object size extends MappedLong(this)
	object maxlength extends MappedLong (this)
	object note extends MappedString (this, 256) 
    object actual extends MappedInt(this)
	object order extends MappedLong (this)
}

object DAO_PAGE_ELEMENTS_001 extends DAO_PAGE_ELEMENTS_001 with LongKeyedMetaMapper[DAO_PAGE_ELEMENTS_001]{
	
   override def dbTableName = "PAGE_ELEMENTS"
   override def fieldOrder = List(page_id, data_id, label_nm, control_id, size, maxlength, note, actual, order)

   def getElementsByActual(page_id:Int, actual:Int):List[DAO_PAGE_ELEMENTS_001] = { 
       DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id),
                                      By(DAO_PAGE_ELEMENTS_001.actual, actual),
                                      OrderBy(DAO_PAGE_ELEMENTS_001.order, Ascending))
   }
   
   def savePageElementsWithValue(page_id:Int, label_nm: String, data_id: Int, control_id: Int, size: Long, 
                                 maxlength: Long, note: String, order:Long, actualOld:Int, actualNew:Int)={
        DAO_PAGE_ELEMENTS_001.findAll(
            By(DAO_PAGE_ELEMENTS_001.page_id, page_id),
            By(DAO_PAGE_ELEMENTS_001.order, order),
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
    }
   
   def getPages(operation_id:Int, page_id:Int)={
	   val pages= DAO_PAGE_001.findAll(By(DAO_PAGE_001.id, page_id),
	  		   						   By(DAO_PAGE_001.operation_id,operation_id))
	   pages
   }
  
   
   def addPageElements(page_nm:String, label_nm:String, data_id:Int, control_id: Int, size:Long, maxlength:Long, note:String, actual:Int, operation_id:Int, page_id:Int)={
    	
    			    val torder = (DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id))).foldLeft(-1)(_ max _.order.toInt) + 1
                    Log.info("CUANDO  HAY ALGO="+operation_id.toString+" "+ page_id.toString+" "+data_id.toString+" "+order.toString)
                    getPages(operation_id, page_id).map(_.page_nm(page_nm)).map(_.save)
    
                      
	    	        DAO_PAGE_ELEMENTS_001.create
	    	         .page_id(page_id)
	    	         .data_id(data_id)
	    	         .label_nm(label_nm)
	    	         .control_id(control_id)
	    	         .size(size)
	    	         .maxlength(maxlength)
	    	         .note(note)
	    	         .order(torder)
	    	         .actual(actual)
	    	         .save
	    	         
	    	        Log.info("ELEMENTOS GUARDADOS="+" "+page_id.toString+" "+ data_id.toString+" "+label_nm.toString+" MAX"+maxlength.toString)
   }
   
   def deletePageElements(page_id:Int, order:Int)={
    	  var delete_data = DAO_PAGE_001.getPage(page_id)
    	  
    	  delete_data match{
    	 	  case (a:DAO_PAGE_001)::Nil => a.status.toInt match{
    	 	 	  case 1|2 => getPageElementsOption(page_id, 1, order).map(_.delete_!)
    	 	 	   			  getPageElementsOption(page_id, 2, order).map(_.delete_!)
    	 	 	  case 4 => getPageElementsOption(page_id, 2, order).map(_.delete_!)
    	 	 	  case _ => Nil
    	 	  }
    	 	  case _ => S.error("error")
    	  }
      }
   
   def getPageElementsOption(page_id:Int, actual:Int, order:Int):List[DAO_PAGE_ELEMENTS_001]={
    	  DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id),
    	 		  						By(DAO_PAGE_ELEMENTS_001.actual, actual),
    	 		  						By(DAO_PAGE_ELEMENTS_001.order, order))
      }

  def getPageByActual(page_id:Int, actual:Int):List[DAO_PAGE_ELEMENTS_001] = { 
       DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id),
                                      By(DAO_PAGE_ELEMENTS_001.actual, actual),
                                      OrderBy(DAO_PAGE_ELEMENTS_001.order, Ascending))
   }   
      
}	    	        					 		
      
		   
