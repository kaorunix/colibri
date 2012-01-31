package ni.edu.uca.colibri.model 
 
import net.liftweb._ 
import mapper._ 
import http._ 
import SHtml._ 
import util._ 
 
class Mapper extends LongKeyedMapper[Mapper] with IdPK { 
 def getSingleton = Mapper

 object name extends MappedString(this, 16)
 object mapper extends MappedString(this, 32) 
 object size extends MappedInt(this) 
} 
 
object Mapper extends Mapper with LongKeyedMetaMapper[Mapper] { 
    override def fieldOrder = List(name, mapper, size)
}
