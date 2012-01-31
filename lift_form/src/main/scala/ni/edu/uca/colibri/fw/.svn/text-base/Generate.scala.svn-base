package ni.edu.uca.colibri.fw

import _root_.java.io._
import ni.edu.uca.colibri.model._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._

case class Generate(operation_id:Long) {
  val pathprefix = "src\\main\\"
  object Log extends Logger

    def mkAll(operation_id: Long){ 
     def printObject(e:Elements):String = { //e.mapper
//       val m = Mapper
       val c = Mapper.findAll(BySql("id =?", IHaveValidatedThisSQL("kaoru", "2011-03-18"), e.mapper))(0)
       Log.info("Mapper:" + c.toString)
//       val s = ",64"
       val s = c.size match { 
           case i if i.toInt > 0 => "," + i.toString
           case _ => ""
       }
       "    object " + e.column + " extends Mapped" + c.mapper + "(this" + s + ")"      

     }

//     val opr=1
     val getQuery = Operation.findAll()
	 val operationName= getQuery.map(_.name)
	 
     val operation=Operation.getOperation(operation_id)(0).name
     val operationData = Elements.getElemensByOperation(operation_id)
     val objects = operationData.map(printObject(_))
      val field = operationData.map(_.column)
      Log.info("objects:" + objects)

      mkHtml(operation)
      mkSnippet(operation)
      mkModel(operation, objects, field.mkString(", "))
      mkObject(operationName.mkString(", "))
      
      
    }


	def mkFile (path :String, contents: String){
		try{
			  val bw = 
			    new BufferedWriter(new FileWriter(path));
			   bw.write(contents)
			   bw.close();
		}catch{
			case e: IOException  => println("IO exception");
			}
		}

	def mkHtml (operation: String){
		val html_file: String = pathprefix + "webapp\\Operation\\" + operation + ".html";
		val html_contents = "<lift:surround with=\"default\" at=\"content\"> \n" +
			  				"   <lift:Util.in> \n" +
			  				"	<lift:" + operation + "Form.add form=\"post\"> \n" +
			  				"	   <elm:submit> \n" +
			  				" 		  <button>submit</button> \n" +
			  				"	   </elm:submit> \n" +
			  				"    </lift:"+ operation +"Form.add> \n" +
			  				"   </lift:Util.in> \n"+
			  				" </lift:surround> \n"

		mkFile(html_file, html_contents)
	}
	
	def mkSnippet (operation: String){
		val snippet_file: String= pathprefix + "scala\\ni\\edu\\uca\\colibri\\snippet\\"+ operation +"Form.scala";
		val snippet_contents:String= "package ni.edu.uca.colibri.snippet  \n\n" +
				  					"import ni.edu.uca.colibri._ \n" +
				  					"import model._ \n" +
				  					"import net.liftweb._ \n" +
				  					"import http._ \n" +
				  					"import SHtml._ \n" +
				  					"import S._ \n" +
				  					"import js._ \n" +
				  					"import JsCmds._ \n" +
				  					"import mapper._ \n" +
				  					"import common._ \n" +
				  					"import util._  \n" +
                                    "import scala.xml.{NodeSeq, Text} \n" +
				  					"import Helpers._ \n\n" +
				  					"class "+ operation +"Form { \n" +
				  					"    def add(form: NodeSeq) = { \n" +
				  					"        val elm = "+ operation +".create \n" +
				  					"	     /*def checkAndSave(): Unit = \n" +
				  					"           elm.validate match { \n" +
				  					"               case Nil => elm.save ; S.notice(\"Added\"+elm.desc) \n" +
				  					"               case xs => S.error(xs) ; S.mapSnippet(\""+ operation +"Form.add\", doBind) \n" +
				  					"           } */\n\n" +
				  					"        def doBind(form: NodeSeq) = \n" +
				  					"            bind(\"elm\", form, \n" +
				  					"                \"submit\" -> elm.toForm(Full(\"submit\"), {_.save })) \n\n" +
				  					"        doBind(form) \n" +
				  					"    } \n" +
				  					"}\n"
		mkFile(snippet_file, snippet_contents)
	}
	
	def mkModel (operation: String, obj: List[String], field: String){
		val model_file: String= pathprefix + "scala\\ni\\edu\\uca\\colibri\\model\\"+ operation +".scala";
		val model_contents: String = 	"package ni.edu.uca.colibri.model  \n\n" +
					  					"import net.liftweb._ \n" +
					  					"import mapper._ \n" +
					  					"import http._ \n" +
					  					"import SHtml._ \n" +
					  					"import util._ \n" +
					  					"import common._ \n\n" +
					  					"class "+ operation +" extends LongKeyedMapper["+ operation +"] with IdPK { \n" +
					  					"    object Log extends Logger \n" +
					  					"    def getSingleton = "+ operation +"\n" +
					  					obj.foldLeft("")(_ + _ + "\n")+ "} \n\n" +
					  					"object "+ operation +" extends "+ operation +" with LongKeyedMetaMapper["+ operation +"] { \n" +
					  					"    override def fieldOrder = List(" + 
                                        field + ") \n" +
					  					"}"

		mkFile(model_file, model_contents)
  }
	def mkObject(operationName: String){
	
		val objects_file: String= "scala\\ni\\edu\\uca\\colibri\\fw\\objectDatabase.scala";
		val objetcs_contents: String= "package ni.edu.uca.colibri.fw  \n\n" +
									  "import _root_.ni.edu.uca.colibri.model._ \n" +
									  "import _root_.net.liftweb.mapper._ \n\n" +							  					
				  					  "object objectDatabase { \n" +
				  					  "val database:Seq[BaseMetaMapper] = Seq(User, Elements, Mapper, Operation, "+ operationName+") \n" +
				  					  "database \n" +
				  					  "}" 
				  	
		
			 
		mkFile(objects_file, objetcs_contents)
		
		}
	


  mkAll(operation_id)

}
