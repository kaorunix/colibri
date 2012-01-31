package ni.edu.uca.colibri.snippet 
 
import scala.xml.{NodeSeq} 
import ni.edu.uca.colibri._ 
import model._ 
 
class Util { 
 def in(html: NodeSeq) = 
   if (User.loggedIn_?) html else NodeSeq.Empty 
 
 def out(html: NodeSeq) = 
   if (!User.loggedIn_?) html else NodeSeq.Empty 
}
