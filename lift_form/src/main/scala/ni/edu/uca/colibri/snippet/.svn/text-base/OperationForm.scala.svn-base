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
   object operation_id extends RequestVar("")

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
           "modify" -> SHtml.submit("modify", doModify(r.id) ),
           "create" -> SHtml.submit("create", doCreate(r.id) ) )
  })
  private def doModify(id:Long)(){
//    SessionObject(id)
    operation_id(id.toString)
    S.redirectTo("/Operation/Column")
//      Operation.find(id).open_!.accept
  }
  private def doCreate(id:Long)(){
      Generate(id)
//      Operation.find(id).open_!.reject
  }
}

//           "hidden" -> SHtml.hidden(Session_Operation_Id(Box(r.id.toString)),r.id.toString),
