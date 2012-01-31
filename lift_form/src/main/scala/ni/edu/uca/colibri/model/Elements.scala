package ni.edu.uca.colibri.model 
 
import net.liftweb._ 
import mapper._ 
import http._ 
import SHtml._ 
import util._ 
import common._


class Elements extends LongKeyedMapper[Elements] with IdPK { 
  object Log extends Logger
 def getSingleton = Elements

 object name extends MappedLongForeignKey(this, Operation) { 
   override def _toForm = { 
     val oprList = Operation.findAll().map(a => (a.id.toString, a.name.toString))
     Full(select(oprList, 
            Empty, 
            f => set(f.toInt))) 
   }

 }
 object column extends MappedString(this, 16) 
 object mapper extends MappedLongForeignKey(this, Mapper) { 
   override def _toForm = Full(
     select(Elements.mapperList, 
            Empty, 
            f => set(f.toInt))) 
 }
 object order extends MappedInt(this) 
 object desc extends MappedPoliteString(this, 128) 

    def getElemensByOperation(operation_id: Long): List[Elements] = { 
//        val op = Operation
//        val opr = Operation.getOperation(operation_id)
        Elements.findAll(By(Elements.name, operation_id), 
                         OrderBy(Elements.order, Ascending))
    }
} 
 
object Elements extends Elements with LongKeyedMetaMapper[Elements] { 
    lazy val mapperList = Mapper.findAll().map(a => (a.id.toString, a.name.toString))
    override def fieldOrder = List(name, column, mapper, order, desc)

  
}
