package ni.edu.uca.colibri.snippet 
 
import ni.edu.uca.colibri._ 
import model._ 
 
import net.liftweb._ 
import http._ 
import SHtml._ 
import S._ 
import js._ 
import JsCmds._ 
import mapper._ 
import common._ 
import util._ 
import Helpers._  
import scala.xml.{NodeSeq, Text} 
import fw._

class OperationForm { 
	object Log extends Logger
	
 def add(form: NodeSeq) = { 
   val elm = Operation.create //.owner(User.currentUser) 
 
    def doBind(form: NodeSeq) =
     bind("elm", form, 
        "submit" -> elm.toForm(Full("submit"), {_.save }))
/*        "create_page" -> SHtml.select(
            Operation.findAll().map(a => (a.id.toString, a.name.toString)), 
            Empty, f => set(f.toInt)),
        "create" -> SHtml.submit("create", mkFiles)*/

     doBind(form) 
   } 
  
  def listForm(form:NodeSeq) = Operation.findAll.flatMap( r => {
      bind("f", form,
           "operation" -> Text( r.name ),
           "modify" -> SHtml.submit("modify", doModify(r.id)),
           "create" -> SHtml.submit("create", doCreate(r.id) ) )
  })


  private def doModify(id:Long)(){
	Operation_Id.operation_idVar(id)	  
    Log.info("id: " + Operation_Id.operation_idVar.get) 
    S.redirectTo("/Operation/Column")
  }
  
  private def doCreate(id:Long)(){
      Generate(id)
//      Operation.find(id).open_!.reject
  }
}

object Operation_Id {
		object operation_idVar extends SessionVar (S.param("operation_id").map(_.toLong)openOr 0L)
	}


