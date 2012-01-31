package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.scala.xml._

case class ListTableType (a: String, b: Long) extends DataType (a, b){
var elements :List[List[DataType]]=Nil
var elements_plan :List[List[DataType]]=Nil
//var elements :List[Any]=Nil
//var elements :List[DataType|List[DataType]]=Nil

	def apply(index: Int) = {
	
		elements(index)
	}
	
	override def copy(): ListTableType = {
		var tcopy = new ListTableType(a, b)
		this.elements_plan.map(c => { 
            val w = (c.foldRight[List[DataType]](Nil)(_.copy::_))
            tcopy.initList(w)})
        this.elements.map(c => { 
            val w = (c.foldRight[List[DataType]](Nil)(_.copy::_))
            tcopy.addList(w)})
                
// => a match {case d:DataType => d.copy})))
		tcopy
	}

    def getCopyClearElements():List[DataType]= { 
        Log.info("ListTableType:getCopyClearElements:size="+ elements_plan.size + " data=" + elements_plan.toString + " elements(0): size=" + elements_plan(0).size + " data=" + elements_plan(0).toString)
        elements_plan(0).map(a => a match {case d:DataType => d.copy})
    }

/*    def getCopyClearElements():List[DataType]= { 
        Log.info("ListTableType:getCopyClearElements:size="+ elements.size + " data=" + elements.toString + " elements(0): size=" + elements(0).size + " data=" + elements(0).toString)
        elements(0).map(a => a match {case d:DataType => d.copy})
    }*/
	
	def add(e: DataType*){
//	def add[T<:DataType|T<:List[DataType]](e: T*){
//	def add(e: Any*){
	    this.addList(List(e:_*))
	}
	

    def addList(e: List[DataType]) { 
        elements = elements:::List(e)
        Log.info("addList AFTER elements:" + elements.toString)
    }
    def initList(e: List[DataType]) { 
        elements_plan = elements_plan:::List(e)
        Log.info("addList AFTER elements_Plan:" + elements_plan.toString)
    }
	override def mkTableTitle(lang_id: Int, operation_id:Int)= {
		  <tr>
		  {(elements(0).map(a =>  
              if (a.isInstanceOf[DataType]) 
                a.asInstanceOf[DataType].toLabel(lang_id, operation_id)
              else "ERROR LIST"
          )).map(mkTh(_))}
		  </tr>
	}	
	
	def mkTh(in: String) = {
		<th>{in}</th>
	}

    def mkTableEmbed()= {
//      Log.info("mkTableEmbed: elements:" + elements.toString)
      Log.info("ListTableType.mkTableEmbed: elements:"+ elements.map(mkTableOneEmbed(_)).toString)
//        elements.map(mkTableOneEmbed(_))
        mkTableOneEmbed(elements_plan(0))
    }

    def mkTableOneEmbed(a:List[DataType]):NodeSeq= { 
          <tr>
      { Log.info("ListTableType.mkTableOneEmbed: List[DataType]:" + a.toString)
        a.map(b => 
            if (b.isInstanceOf[DataType]) { 
                Log.info("ListTableType.mkTableOneEmbed:contents:"+ b.asInstanceOf[DataType].embedItem)
                b.asInstanceOf[DataType].embedItem }

            else {Log.info("mkTableOneEmbed:"+b.toString); <DIV />}).map(mkTd(_))}
          </tr>
    } 

	def mkTd(in:NodeSeq)= {
		<td>{in}</td>
		
	}
	
    def clear() { 
        elements.map(b => b.map(a =>
          if (a.isInstanceOf[DataType]) a.asInstanceOf[DataType].setValue("")
          else false))
    }

    override def setOptions(lang_id:Int, operation_id:Int) { 
        elements.map(b => b.map(a =>
          a.setOptions(lang_id, operation_id))
        )
    }
	
	/*	def mkTh(f: Int => NodeSeq): (DataType => NodeSeq) = {
		<th>{data.f(lang_id)}</th>
	}*/
}
	
	
