package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.scala.xml._

case class TableType (a: String, b: Long) extends DataType (a, b){
var elements :List[DataType]=Nil

	def apply(index: Int) = {
	
		elements(index)
	}
	
	override def copy(): TableType = {
		var tcopy = new TableType(a, b)
		this.elements.map(a => a match {
            case l:ListTableType => tcopy.add(l.copy)
            case d:DataType => tcopy.add(d.copy)
        })
		tcopy
	}
	
	def add(e: DataType*){
		elements=elements:::List(e:_*)
	}
	
	override def mkTableTitle(lang_id: Int, operation_id:Int)= {
		  <tr>
		  {(elements.map(_.toLabel(lang_id, operation_id))).map(mkTh(_))}
		  </tr>
	}	
	
	def mkTh(in: String) = {
		<th>{in}</th>
	}

    def mkTableEmbed()= {
        <tr>
          {(elements.map(_.embedItem)).map(mkTd(_))}
        </tr>
    }

	def mkTd(in:NodeSeq)= {
		<td>{in}</td>
		
	}
	
    def clear() { 
        elements.map(_.setValue(""))
    }

    override def setOptions(lang_id:Int, operation_id:Int) { 
        elements.map(_.setOptions(lang_id, operation_id))
    }
	
	/*	def mkTh(f: Int => NodeSeq): (DataType => NodeSeq) = {
		<th>{data.f(lang_id)}</th>
	}*/
}
	
	
