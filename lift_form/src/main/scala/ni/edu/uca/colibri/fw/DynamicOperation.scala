package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.model._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import net.liftweb.mapper._
import util._


class DynamicOperation {

	val getQuery = Operation.findAll()
	val operationName= getQuery.map(_.name)
	
	
	def mkMenu():List[Menu] ={
		def OperationMenu(op_name:String)={
			
			Menu(Loc(op_name, List("Operation", op_name), op_name))
            //Menu(Loc(, List("Operation", "Operation"), "Operation"))
		}
		operationName.map(OperationMenu(_))
	}
	/*
	
	def mkDatabase()={
		def database (operationName:String) = {
			val name = Class.forName("ni.edu.uca.colibri.model."+operationName)
		}	
		operationName.map(database(_))
	}*/
	

}