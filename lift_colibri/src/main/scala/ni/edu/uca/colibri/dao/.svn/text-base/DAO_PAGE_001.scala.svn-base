package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.http._
import _root_.ni.edu.uca.colibri.odo._
import _root_.net.liftweb.common.Logger

class DAO_PAGE_001 extends LongKeyedMapper[DAO_PAGE_001] with IdPK  {
    object Log extends Logger
	
	def getSingleton = DAO_PAGE_001
	object page_nm extends MappedString (this,16)
	object operation_id extends MappedLongForeignKey (this, DAO_OPERATION_001)	
	object status extends MappedInt (this) //v_status
	object organization_nm extends MappedString (this, 16)
	object login_nm extends MappedLongForeignKey (this,DAO_LOGIN_USER_001)
	object update_dt extends MappedDateTime(this)
	object panel_id extends MappedInt (this)
	object page_tp extends MappedInt (this) //v_page_tp
    object delete_fg extends MappedInt(this)
}

object DAO_PAGE_001 extends DAO_PAGE_001 with LongKeyedMetaMapper[DAO_PAGE_001]{
	
   override def dbTableName = "PAGE"
   override def fieldOrder = List(page_nm, operation_id, status, organization_nm, login_nm, update_dt, panel_id, page_tp)
   
   def getAll = {DAO_PAGE_001.findAll}

   def getPageByStatus(status: Seq[Int]): List[DAO_PAGE_001] = {
       DAO_PAGE_001.findAll(ByList(DAO_PAGE_001.status, status))
   }

   def count_elem_num(page_id: Long) = {
     Log.info("count_elem_num:" + page_id+ " START")
//       var elm_num = DAO_PAGE_ELEMENTS_001.countByInsecureSql("select count (label_nm) from page_elements where page_id = "+ page_id,
//                IHaveValidatedThisSQL("carlosd","21/04/2011"))
     var ret:Int =        DAO_PAGE_ELEMENTS_001.findAll(By(DAO_PAGE_ELEMENTS_001.page_id, page_id)).size
     Log.info("count_elem_num:" + page_id+ " count="+ret)
     ret
   }
   def getPageByOperation(operation_id:Int, mode:Int):List[DAO_PAGE_001] = {
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
	  DAO_PAGE_001.findAll(
	 		  By(DAO_PAGE_001.operation_id, operation_id),
	 		  ByList(DAO_PAGE_001.status, statusOptions),
	 		  By(DAO_PAGE_001.delete_fg, 0))
   }
  def getPageForMode(page_id:Int, mode:Int) ={
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
    DAO_PAGE_001.findAll(
      By(DAO_PAGE_001.id, page_id),
      ByList(DAO_PAGE_001.status, statusOptions),
      By(DAO_PAGE_001.delete_fg, 0))
  }
   
   def getPageByName(page_nm:String, page_id:Int)={
    	  DAO_PAGE_001.findAll(BySql("page_nm=? and id=?", IHaveValidatedThisSQL("dchenbecker", 
                              "2008-12-03"), page_nm, page_id))
      }
   def getPage(page_id:Int)={
	   DAO_PAGE_001.findAll(BySql("id=?", IHaveValidatedThisSQL("carlosd","2011-13-04"), page_id))
   }
   
   def CancelPageList(page_id:Int, mode:Int)={
    	  var Cancel= DAO_PAGE_001.getPage(page_id:Int)match{
    	  case (a:DAO_PAGE_001)::Nil => {
    	 	  mode match{
    	 	  	case 2 => if (a.status.toInt==4){
    	 	 	        a.status(4).delete_!
    	 	            DAO_PAGE_ELEMENTS_001.getPageByActual(page_id,2).map(_.delete_!)}
    	 	  	case 3 => DAO_PAGE_ELEMENTS_001.getPageByActual(page_id,2).map(_.delete_!) 
    	 	  	case _ => Nil
    	 	  } 
    	  }
          case _ => Nil
    	  }

      }
   
   def copyPage(page_id:Int) = {
	   var pageList = DAO_PAGE_001.findAll(BySql("id=?", IHaveValidatedThisSQL("carlosd", "2011-13-04"), page_id))
	   pageList match{
	  	   case (a:DAO_PAGE_001)::Nil => 
	  	     DAO_PAGE_001.create
	  	     	.page_nm(a.page_nm)
	  	     	.operation_id(a.operation_id)
	  	     	.status(a.status)
	  	     	.organization_nm(a.organization_nm)
	  	     	.login_nm(a.login_nm)
	  	     	.update_dt(new java.util.Date)
	  	     	.panel_id(a.panel_id)
	  	     	.page_tp(a.page_tp)
	  	     	.delete_fg(a.delete_fg)
	  	     	.save;
	  	   case _ => Nil; S.error("PAGE NOT CREATED")
	   }
   }
   // def deletePage(page_id:Int)={
//	   var del = DAO_PAGE_001.findAll(BySql("id=?", IHaveValidatedThisSQL("carlosd",
//	  		   "2011-13-04"), page_id))
//	  	del(0).status(1).save
//   }
//   def updatePage(page_id:Int, mode:Int) = {
//     updatePage("", page_id, mode)
//   }

   def updatePage(page_nm: String, page_id:Int, mode:Int) = {
      val page:DAO_PAGE_001 = DAO_PAGE_001.getPage(page_id) match{
	 	  case (a:DAO_PAGE_001)::Nil => a
          case _ => None.asInstanceOf[DAO_PAGE_001]
      }
      mode match { 
        // Ref
        case 1 => S.error("Error")

        // Add
        case 2 if (page.status.toInt == 4) =>
                    page.status(1) 
                             .page_nm(page_nm)
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        // Mod
        case 3 => { 
            
                    page.status(page.status.toInt match { 
                                case 0 => 2
                                case _ => page.status.toInt
                              }) 
                             .page_nm(page_nm)
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        }
        // Del
        case 4 => if (page.status.toInt == 0) { 
                    page.status(3) 
                             .page_nm(page_nm)
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
        }
        // Undo
        case 5 => page.status.toInt match { 
            case 1|2|3 => page.status(0)
                             .page_nm(page_nm)
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
	 	    case _=> S.error("Error")
        }
        // App
        case 6 => page.status.toInt match { 
              // Create -> Approved
              // Modified -> Approved
	 	 	  case 1|2 => page.status(0)
                             .organization_nm("uca")
                             .login_nm(1)
                             .update_dt(new java.util.Date())
                             .save
              // Deleted -> Approved
	 	 	  case 3 => page.status(0)
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
   
  def updateForPageTp(page_id:Int, page_tp:Int)={ 
      val page:DAO_PAGE_001 = DAO_PAGE_001.getPage(page_id) match{
	 	  case (a:DAO_PAGE_001)::Nil => a
          case _ => None.asInstanceOf[DAO_PAGE_001]
      }
      page.page_tp(page_tp).save
  }
   
}
