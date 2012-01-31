package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.Logger
import _root_.ni.edu.uca.colibri.fw._
import _root_.net.liftweb.http._


class DAO_OPERATION_DATA_001 extends LongKeyedMapper[DAO_OPERATION_DATA_001] with IdPK {
 
	object Log extends Logger
	
	def getSingleton = DAO_OPERATION_DATA_001
    object operation_id extends MappedLongForeignKey(this, DAO_OPERATION_001)
	object data_nm extends MappedString(this, 16)
	object data_type extends MappedInt(this)   //v_type 
	object size extends MappedLong(this)
	object null_allowed extends MappedInt(this) // v_null_allowed
	object order extends MappedLong (this)
	object actual extends MappedInt(this)    // v_actual
	
}

   object DAO_OPERATION_DATA_001 extends DAO_OPERATION_DATA_001 with LongKeyedMetaMapper[DAO_OPERATION_DATA_001]{  
   override def dbTableName = "OPERATION_DATA"
   override def fieldOrder = List(operation_id, data_nm, data_type, size, null_allowed, order, actual)
    
   def getDetail(operation_id:Int)= {
	   val operation_data = DAO_OPERATION_DATA_001.findAll(BySql("operation_id=?", IHaveValidatedThisSQL("dchenbecker", 
                              "2008-12-03"), operation_id))
       operation_data
   	}

   def convert(t:TableType)(o:DAO_OPERATION_DATA_001) = {
	   
	   t(0).setValue(o.data_nm)
	   t(1).setValue(o.data_type)
	   t(2).setValue(o.size)
	   t(3).setValue(o.null_allowed)
	   Log.info("T elements:"+t+" t0" + t(0).getValue.toString)
	   t
	   
   }
   def search(t: TableType, operation_id: Int):List[TableType] = {
	   getDetail(operation_id).map(convert(t.copy)(_))	  
   }
    def addOperationData(operation_nm:String, description:String, data_nm:String, 
		   data_type:Int, size:Int, null_allowed:Int, order:Long, actual:Int)={
    	  DAO_OPERATION_001.getOperationByName(operation_nm) match {
    			case (a:DAO_OPERATION_001)::Nil => 
                    val torder = (DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.operation_id, a.id.toInt))).foldLeft(-1)(_ max _.order.toInt) + 1
    			    DAO_OPERATION_DATA_001.create
    				.operation_id(a.id)
    				.data_nm(data_nm)
    				.data_type(data_type)
                    .size(size)
    				.null_allowed(null_allowed)
    				.order(torder)
    				.actual(actual)
    				.save
    			case _ =>  
	    	       DAO_OPERATION_001.create
		       		.operation_nm(operation_nm)
		       		.description(description)
		       		.status(4)
		       		.save
		       		val a = DAO_OPERATION_001.getOperationByName(operation_nm)
//                    Log.info("order:" + torder)
		       		DAO_OPERATION_DATA_001.create
    				.operation_id(a(0).id)
    				.data_nm(data_nm)
    				.data_type(data_type)
                    .size(size)
    				.null_allowed(null_allowed)
    				.order(1)
    				.actual(actual)
    				.save
    	  }
      }
   
    def saveOperationData (operation_id:Int)={
        getDetail(operation_id).map(_.actual(1)).map(_.save)
    }
    def saveOperationDataWithValue(operation_id:Int, 
                                    data_nm: String, 
                                    data_type: Int, 
                                    size: Int, 
                                    null_allowed: Int, 
                                    order:Long, 
                                    actualOld:Int,
                                    actualNew:Int)={
        DAO_OPERATION_DATA_001.findAll(
            By(DAO_OPERATION_DATA_001.operation_id, operation_id),
            By(DAO_OPERATION_DATA_001.order, order),
            By(DAO_OPERATION_DATA_001.actual, actualOld))
        match { 
            case (a:DAO_OPERATION_DATA_001)::Nil => { 
    Log.debug("saveOperationDataWithValue operation_id:" + operation_id+ " data_nm:"+ a.data_nm.toString + "->" + data_nm+ "  order:"+ order)
                a.data_nm(data_nm)
                 .data_type(data_type)
                 .size(size)
                 .null_allowed(null_allowed)
                 .actual(actualNew)
                 .save
            }
            case _ => DAO_OPERATION_DATA_001.create
                  .operation_id(operation_id)
                  .data_nm(data_nm)
                  .data_type(data_type)
                  .size(size)
                  .null_allowed(null_allowed)
                  .order(order)
                  .actual(actualNew)
                  .save
        }
    }

   def getOperationDataByActual(operation_id:Int, actual:Int):List[DAO_OPERATION_DATA_001] = { 
       DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.operation_id, operation_id),
                                      By(DAO_OPERATION_DATA_001.actual, actual),
                                      OrderBy(DAO_OPERATION_DATA_001.order, Ascending))
   }   
   
   
    
     def getOperationDataOption(operation_id:Int, actual:Int, order: Int):List[DAO_OPERATION_DATA_001] = { 
       DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.operation_id, operation_id),
                                      By(DAO_OPERATION_DATA_001.actual, actual),
                                      By(DAO_OPERATION_DATA_001.order, order))
   } 


   def getOperationDataNameById(operation_id:Int, id:Long):String= { 
         val d = DAO_OPERATION_DATA_001.findAll(
                 By(DAO_OPERATION_DATA_001.operation_id, operation_id),
                 By(DAO_OPERATION_DATA_001.id, id))
         if (d.size > 0) d(0).data_nm.toString else ""
   }
   def getOperationDataNamesListByOperationId(operation_id:Int)= { 
         val d = DAO_OPERATION_DATA_001.findAll(
                 By(DAO_OPERATION_DATA_001.operation_id, operation_id),
                 OrderBy(DAO_OPERATION_DATA_001.order, Ascending))

         if (d.size > 0) d(0).data_nm.toString else ""
   }
   
   def getSelectListData_nm (operation_id:Int)= { 
       val result = DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.operation_id, operation_id))
       result.map(a => (a.id.toString, a.data_nm.toString))
   }
   
  def approveOperationData(operation_id:Int) = {
	  var approved_data= DAO_OPERATION_001.getOperation(operation_id)
                   
        approved_data match{
	 	  case (a:DAO_OPERATION_001)::Nil => a.status.toInt match{
	 	 	  case 1=>  getDetail(operation_id).map(_.actual(0)).map(_.save)
	 	 	  case 2=>  getOperationDataByActual(operation_id, 0).map(_.delete_!)
	 	 			  	getDetail(operation_id).map(_.actual(0)).map(_.save)
	 	 	  case _ => Nil
	 	 	  
	 	  }
	 	  case _ => S.error("error") 
	  }
   }
//  Nuevo
  def undoOperationData(operation_id:Int, mode:Int)={
	  var undo_data = DAO_OPERATION_001.getOperation(operation_id)
	  
	  undo_data match{
	 	  case (a:DAO_OPERATION_001)::Nil => a.status.toInt match{
	 	 	  case 1 => getOperationDataByActual(operation_id,1).map(_.delete_!)
	 	 	  			DAO_OPERATION_001.getOperation(operation_id).map(_.delete_!)
	 	 	  
	 	 	  case 2|3 => getOperationDataByActual(operation_id,1).map(_.delete_!)
	 	 	  			  DAO_OPERATION_001.updateOperation(operation_id, mode)
	 	 	   				
	 	  }
	 	  case _ => S.error("error")
	  }
  }
  
  
def deleteOperationData(operation_id:Int, order:Int) = {
	
	  var delete_data= DAO_OPERATION_001.getOperation(operation_id)
     
        delete_data match{
	 	  case (a:DAO_OPERATION_001)::Nil => a.status.toInt match{
	 	 			case 0|1|2=>  getOperationDataOption(operation_id, 1, order).map(_.delete_!)
	 	 	  					getOperationDataOption(operation_id, 2, order).map(_.delete_!)
	 	 	  		case 4=>  getOperationDataOption(operation_id, 2, order).map(_.delete_!)
	 	 	  		case _ => Nil
	 	 	  
	 	  }
	 	  case _ => S.error("error") 
	  }
   }
  
      def getDataName(id:DAO_PAGE_ELEMENTS_001):String={ 
        DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.id, id.data_id)) match { 
          case (a:DAO_OPERATION_DATA_001)::Nil => a.data_nm
          case _ => "getDataName.ERROR"
        }
      }


      def getDataType(id:DAO_PAGE_ELEMENTS_001):Pair[Int,Int]={ 
        DAO_OPERATION_DATA_001.findAll(By(DAO_OPERATION_DATA_001.id, id.data_id)) match { 
          case (a:DAO_OPERATION_DATA_001)::Nil => Pair(a.data_type.toInt, a.size.toInt)
          case _ => Pair(-1,-1)
        }
      }
}
