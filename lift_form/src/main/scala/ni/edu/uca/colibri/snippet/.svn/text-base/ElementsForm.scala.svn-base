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
import _root_.java.lang._
 
class ElementsForm { 
  object Log extends Logger
   object operation_id extends RequestVar("") //"S.param("operation_id"))
//   object Session_Operation_Id extends RequestVar(Full("operation_id"))
   Log.info("ElementsForm:init:operation_id: "+ operation_id.get)
//   Log.info("ElementsForm:init:operation_id:map: "+ Session_Operation_Id.openOr("error"))
 def add(form: NodeSeq) = { 
   val elm = Elements.create //.owner(User.currentUser) 
   Log.info("ElementsForm:add:operation_id: "+ operation_id.is.toString)
//   Log.info("ElementsForm:add:operation_id:map: "+ Session_Operation_Id.openOr("error"))
//   elm.name = Long.parseLong(SessionObject.modifyOp.is)

 
   def checkAndSave(): Unit = 
   elm.validate match { 
    case Nil => elm.save ; S.notice("Added "+elm.desc) 
    case xs => S.error(xs) ; S.mapSnippet("Elements.add", doBind) 
   } 
 
   def doBind(form: NodeSeq) = 
   bind("elm", form, 
        "submit" -> elm.toForm(Full("submit"), {_.save }))
 
   doBind(form) 
 } 
}

/*      "name" -> elm.name.toForm, 
      "column" -> elm.column.toForm, 
      "mapper" -> elm.mapper.toForm, 
      "order" -> elm.order.toForm, 
      "desc" -> elm.desc.toForm, 
      "submit" -> submit("New", checkAndSave)) */
