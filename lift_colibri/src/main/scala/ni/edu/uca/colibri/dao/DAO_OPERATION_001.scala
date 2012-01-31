package ni.edu.uca.colibri.dao


import _root_.net.liftweb.mapper._
import net.liftweb._
import http._
import SHtml._
import S._
import js._
import JsCmds._
import mapper._
import util._
import Helpers._


class DAO_OPERATION_001 extends LongKeyedMapper[DAO_OPERATION_001] with IdPK {
//class DAO_LANGUAGEL_001 extends KeyedMapper[Long, DAO_LANGUAGEL_001] {

	def getSingleton = DAO_OPERATION_001
	object operation_nm extends MappedString (this,64)
	object status extends MappedInt (this) //v_status
	object organization_nm extends MappedString (this, 16)//ForeignKey (this, DAO_ORGANIZATION_001)
	object login_nm extends MappedLongForeignKey (this,DAO_LOGIN_USER_001)
//	object user_nm extends MappedLongForeignKey (this,DAO_LOGIN_USER_001) No usamos user_nm
	object description extends MappedString (this, 64)
	object update_dt extends MappedDateTime(this)
	object delete_fg extends MappedInt(this) //v_delete_fg
	
}

object DAO_OPERATION_001 extends DAO_OPERATION_001 with LongKeyedMetaMapper[DAO_OPERATION_001]{
//object DAO_LOGIN_001 extends DAO_LOGIN_001 with KeyedMetaMapper[Long,DAO_LOGIN_001]{
   override def dbTableName = "OPERATION"
   override def fieldOrder = List(operation_nm, status, organization_nm, login_nm, description, update_dt, delete_fg)

   
   def getOperation(operation_id:Int) = {
	  DAO_OPERATION_001.findAll(BySql("id=?", IHaveValidatedThisSQL("dchenbecker", 
                              "2008-12-03"), operation_id))
   }
 
   def getOperationByStatus(status:Seq[Int]):List[DAO_OPERATION_001] = { 
       DAO_OPERATION_001.findAll(ByList(DAO_OPERATION_001.status, status), By(DAO_OPERATION_001.delete_fg, 0))
   }
   
   def count_data_quantity(operation_id:Long)={
	   var data_quantity = DAO_OPERATION_DATA_001.countByInsecureSql("select count (data_nm) from operation_data where operation_id="+operation_id+"",
	  		   										IHaveValidatedThisSQL("Diego Caicedo", "18/04/2011"))
	   data_quantity
   }
  
        /*
    
    def UpdateOperationStatus(operation_id:Int) = {
	  var update_status= DAO_OPERATION_001.getOperation(operation_id)
                   
        update_status match{
	 	  case (a:DAO_OPERATION_001)::Nil => a.status.toInt match{
	 	 	  case 3 => a.status(0).save;
	 	 	           a.delete_fg(1).save
	 	 	  case _=> a.status(0).save
	 	  }
	 	  
	 	  case _=> S.error("Error")          
	  }   
   }*/
   def updateOperation(operation_id:Int, mode:Int) = {
      val operation:DAO_OPERATION_001 = DAO_OPERATION_001.getOperation(operation_id) match{
	 	  case (a:DAO_OPERATION_001)::Nil => a
          case _ => None.asInstanceOf[DAO_OPERATION_001]
      }
      mode match { 
        // Ref
        case 1 => S.error("Error")

        // Add
        case 2 if (operation.status.toInt == 4) =>
                    operation.status(1) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        // Mod
        case 3 => { 
            
                    operation.status(operation.status.toInt match { 
                                case 0 => 2
                                case _ => operation.status.toInt
                              }) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        }
        // Del
        case 4 => if (operation.status.toInt == 0) { 
                    operation.status(3) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        }
        // Undo
        case 5 => operation.status.toInt match { 
            case 1|2|3 => operation.status(0) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
	 	    case _=> S.error("Error")
        }
        // App
        case 6 => operation.status.toInt match { 
              // Create -> Approved
              // Modified -> Approved
	 	 	  case 1|2 => operation.status(0) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
              // Deleted -> Approved
	 	 	  case 3 => operation.status(0) 
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new  java.util.Date())
                             .delete_fg(1)
                             .save
	 	 	  case _=> S.error("Error")
        }
	 	case _=> S.error("Error")          
	  }   
   }
   
      def DeleteOperation(operation_id:Int)={
    	  var del=DAO_OPERATION_001.findAll(BySql("id=?",IHaveValidatedThisSQL("carlosd", 
    	 		  "2011-03-30"),operation_id))
    	  del(0).status(1).save
    	  //del(0).delete_fg(1).save
      }
      
      def CancelOpr(operation_id:Int, mode:Int)={
    	  var Cancel= DAO_OPERATION_001.getOperation(operation_id:Int)match{
    	  case (a:DAO_OPERATION_001)::Nil => {
    	 	  mode match{
    	 	  	case 2 => if (a.status.toInt==4){
    	 	 	        a.status(4).delete_!
    	 	            DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id,2).map(_.delete_!)}
    	 	  	case 3 => DAO_OPERATION_DATA_001.getOperationDataByActual(operation_id,2).map(_.delete_!) 
    	 	  	case _ => Nil
    	 	  } 
    	  }
          case _ => Nil
    	  }

      }
      
      def getOperationByName(operation_nm:String)={
    	  DAO_OPERATION_001.findAll(BySql("operation_nm=?", IHaveValidatedThisSQL("dchenbecker", 
                              "2008-12-03"), operation_nm))
      }


      def getOperationForMode(operation_id:Int, mode:Int) ={
          val statusOptions = { 
              mode match { 
                  case 1 => Seq(0,1,2,3)
                  case 2 => Seq(4)
                  case 3 => Seq(0,1,2)
                  case 4 => Seq(0)
                  case 5 => Seq(1,2,3)
                  case 6 => Seq(1,2,3)
              }
          }
          DAO_OPERATION_001.findAll(
                By(DAO_OPERATION_001.id, operation_id),
                ByList(DAO_OPERATION_001.status, statusOptions),
                By(DAO_OPERATION_001.delete_fg, 0))
      }
}

 
