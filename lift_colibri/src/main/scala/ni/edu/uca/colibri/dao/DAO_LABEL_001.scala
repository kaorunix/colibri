package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common.Logger

class DAO_LABEL_001 extends LongKeyedMapper[DAO_LABEL_001] with IdPK {
	object Log extends Logger

	def getSingleton = DAO_LABEL_001
//	def primaryKeyField = label_id
	object label_nm extends MappedString(this, 16)
	object lang_id extends MappedLongForeignKey(this, DAO_LANGUAGE_001)
	object label extends MappedString(this, 64) 
	object operation_id extends MappedLong(this) 
}

object DAO_LABEL_001 extends DAO_LABEL_001 with LongKeyedMetaMapper[DAO_LABEL_001]{

   override def dbTableName = "LABEL"
   override def fieldOrder = List(label_nm, lang_id, label, operation_id)
    
   def getLabel(lang_id:Int, label_nm: String, operation_id:Long):String = {
     Log.info("getLabel lang_id=" + lang_id+ " label_nm=" + label_nm + " operation_id=" +operation_id)
	   val query = DAO_LABEL_001.findAll(By(DAO_LABEL_001.lang_id, lang_id),
                                         By(DAO_LABEL_001.label_nm, label_nm),
                                         ByList(DAO_LABEL_001.operation_id, Seq(operation_id, 1L)))

      query match { 
        case (a:DAO_LABEL_001)::Nil => a.label.toString
//        case (a:List[DAO_LABEL_001]) => a.head.label.toString
        case _ => Log.debug("ERROR de LABEL:"+ label_nm); "ERROR de LABEL:"+ label_nm
      }
   }
   
    def getSelectListLabel(oprId:Int, lang_id:Int)= { 
       val result = DAO_LABEL_001.findAll(ByList(DAO_LABEL_001.operation_id, Seq(1L,oprId.toLong)),
    		   							  By(DAO_LABEL_001.lang_id, lang_id))
       result.map(a => (a.label_nm.toString, a.label_nm.toString))
   }
   def getSelectListLabelForPage(oprId:Int, lang_id:Int)= { 
       val result = DAO_LABEL_001.findAll(By(DAO_LABEL_001.operation_id, oprId),
    		   							  By(DAO_LABEL_001.lang_id, lang_id))
       result.map(a => (a.label_nm.toString, a.label_nm.toString))
   }
  
}
  
  
   
