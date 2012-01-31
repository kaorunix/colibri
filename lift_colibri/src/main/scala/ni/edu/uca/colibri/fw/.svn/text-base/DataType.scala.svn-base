package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.scala.xml._
import _root_.net.liftweb.http._
import _root_.net.liftweb.common._
import _root_.net.liftweb.common.Logger
case class DataType(var label_nm: String, var operation_id: Long) extends AbstractDataType(label_nm, operation_id) {
	object Log extends Logger
	//var name = nm
//	var lang_id: Int = 1
//	val label_nm = lbnm
//	val operation_id = oprId
	var value: Any = ""
	var control: Map[Int, Control] = { 
	    Map((1 -> new Control), 
            (2 -> new Control),
            (3 -> new Control),
            (4 -> new Control),
            (5 -> new Control),
            (6 -> new Control) )
    }
	var size: Int = 0
//	var dataType = 0
//    var data_id = 0
//    var message_nm = ""
//    var embedItem = <DIV />
	var listOptions = Seq(("0","None"))
    var realValue_fg = true
    var validation_nm = ""
    var getVisualValue:((Int, Int, String) => String) = { 
        (lang_id:Int, operation_id:Int, a:String) => { 
          val num = { try { a.toInt } catch { case e:NumberFormatException => -1 }}
          Log.info("getVisualValue: " + validation_nm + " lang:" + lang_id)
        DAO_SYSTEM_VALIDATION_001.toLabel(validation_nm, num, lang_id)
        }
    }
	var checked = false
	
	var applyLang_fg = false
//	var value: List[DataType]
	// 1 Ref, 2 Add, 3 Mod, 4 Del, 5 Undo, 6 App
	//TODO Puede cambiar mejorado

	/*def mapControl(c:Map[Int, Control], n:Int): Map[Int, Control] = {
			c = c + (n -> new Control())
	}*/
	
	def getControl(index: Int): Control ={
		control(index)
	}
//	def toLabel(lang_id: Int) = {
//	    DAO_LABEL_001.getLabel(lang_id, label_nm, 1)
//	}
//	def toLabel(lang_id: Int, operation_id: Int) = {
//	    DAO_LABEL_001.getLabel(lang_id, label_nm, operation_id)
//	}
//	def toMessage(lang_id: Int) = {
//	    DAO_MESSAGE_001.getMessage(lang_id, message_nm, operation_id)
//	}
//	def content (operation_id:Int)={
//		DAO_OPERATION_DATA_001.getDetail(operation_id)
//	}
//	def setLangId(id:Int) = {lang_id = id}
//	def setLabelNm(nm:String) = {label_nm = nm}
//	def setOperationId(id:Long) = {operation_id = id}
    def setValidation(validation_nm:String) { 
        this.validation_nm = validation_nm
        listOptions = DAO_SYSTEM_VALIDATION_001.getSelectList(validation_nm)
    }
    def setOptions(lang_id: Int, operation_id:Int) { 
        this.lang_id = lang_id
//        this.operation_id = operation_id.toLong
    }
  

	def toHtml() = {
       <span>{value.toString}</span>
	}
	def toListTable = {
		
	}

	def toTableTitle = {
		
	}
/*	def toTableElement(lang_id: Int) = {
      <tr>
        <th>{toLabel(lang_id)}</th>
        <td>{embedItem} </td>
      </tr>
	}*/
		
	def mkTableTitle(lang_id: Int, operation_id:Int) = {
		<div />
	}

	def mkTableTitle(lang_id: Int):NodeSeq = {
        mkTableTitle(lang_id, 1)
	}

	override def copy(): DataType = {
	    var dcopy = new DataType(label_nm, operation_id)
        dcopy.value = this.value
        dcopy.listOptions = this.listOptions
      (1 to 6).map(i => dcopy.getControl(i).setControl(this.getControl(i).control, this.getControl(i).size, this.getControl(i).maxlength))
        dcopy.lang_id = this.lang_id
//        dcopy.operation_id = this.operation_id
        dcopy.realValue_fg = this.realValue_fg
        dcopy.getVisualValue = this.getVisualValue
        dcopy.checked = this.checked
        dcopy.embedItem = this.embedItem
        dcopy
	}
	def setValue(dt:DataType) = {
		
	}
	def setValue(s: Any) = {
		value = s
	}
	def setValue(n: Number) = {
		
	}
/*	def setValue(l: List[String]) = {
		
	}*/
	
    implicit def DataType2TinyType(d:DataType) { 
      value match { 
        case s:String if (s.length <= 16) => d.asInstanceOf[TinyType]
        case _ => Null
      }
    }
    implicit def DataType2SmallType(d:DataType) { 
      value match { 
        case s:String if (s.length > 16 && s.length <= 64) => d.asInstanceOf[SmallType]
        case _ => Null
      }
    }
    implicit def DataType2MidiumType(d:DataType) { 
      value match { 
        case s:String if (s.length > 64 && s.length < 256) => d.asInstanceOf[MidiumType]
        case _ => Null
      }
    }
    implicit def DataType2LargeType(d:DataType) { 
      value match { 
        case s:String if (s.length > 256) => d.asInstanceOf[LargeType]
        case _ => Null
      }
    }
    implicit def DataType2IntType(d:DataType) { 
      value match { 
        case s:Int => d.asInstanceOf[IntType] 
        case _ => Null
      }
    }
    implicit def DataType2NumberType(d:DataType) { 
      value match { 
        case s:Int => d.asInstanceOf[NumberType] 
        case _ => Null
      }
    }

	def applyLang(lang_id:Int)={
	   if (!applyLang_fg) {
		   listOptions = listOptions.map(a => (a._1, DAO_LABEL_001.getLabel(lang_id, a._2, operation_id.toLong)))
		   applyLang_fg = true
	   }
	}

	
	
	def getValue() = {
        if (realValue_fg) getRealValue
        else getVisualValue(lang_id,operation_id.toInt, getRealValue.toString)
	}
    def getRealValue() = { 
        value
    }

	//List(getValue.toString).map(a => a -> a)
	def toForm(mode: Int):NodeSeq = {
           def printForm(c: Control):NodeSeq= { 
        	
               c.control match  { 
                   	case 0 => Text("")
                   	case 1 => Text(getValue.toString)
                    case 2 => SHtml.text(getValue.toString, setValue(_))
                    case 3 => SHtml.password(getValue.toString, setValue(_))
                    case 4 => SHtml.radio(List(getRealValue.toString), Empty, (s:String) =>  if (s == getValue.toString) checked=true/*, ("name","g1")*/).flatMap(_.xhtml) 
                    																													//establece el nombre de grupo de radio para seleccionar 
                    																												    //un solo radio button
                    case 5 => SHtml.checkbox(false, if (_) checked=true)
                    case 7 => SHtml.select(listOptions, Box(getRealValue.toString), setValue(_)) 
                    case 10 => SHtml.hidden(getValue) 
                    case _ => Text("ERROR") 
              }
          }

          printForm(this.control(mode))
       }

}
