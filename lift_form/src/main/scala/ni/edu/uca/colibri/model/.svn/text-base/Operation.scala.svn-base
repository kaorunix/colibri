package ni.edu.uca.colibri.model 
 
import net.liftweb._ 
import mapper._ 
import http._ 
import SHtml._ 
import util._ 
 
class Operation extends LongKeyedMapper[Operation] with IdPK { 
 def getSingleton = Operation

 object name extends MappedString(this, 16)
  def getOperation(operation_id: Long) :List[Operation]= { 
      Operation.findAll(By(Operation.id, operation_id))
  }
} 
 
object Operation extends Operation with LongKeyedMetaMapper[Operation] { 

}
