package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.scala.xml._

case class TinyType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:String) if a.length <= 16 => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}

case class SmallType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:String) if a.length > 16 && a.length <= 64 => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}

case class MidiumType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:String) if a.length > 64 && a.length <= 256 => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}

case class LargeType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:String) if a.length > 256 => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}
	
	
case class NumberType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:Double) => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}
	
	
case class IntType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:Int) => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}
	
	
/*case class DateType (a: String, b: Long) extends DataType (a, b){

    override def setValue(s: Any):Unit = s match { 
        case (a:Date) => super.setValue(a)
        case _ => throw(new IllegalArgumentException)
    } 

}*/
	
	
