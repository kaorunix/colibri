package ni.edu.uca.colibri.odo

import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._
import ni.edu.uca.colibri.snippet._
import _root_.net.liftweb.common.Logger

class ODO_ADM004 {
	object Log extends Logger
   
	var page_name = "ADM004"
	var title = DataType("Elements_title", 1)
	var page_nm = DataType("Page_Name", 1)

    var elm_order = DataType("nbsp",1)
    var elm_order2 = DataType("nbsp",1)
    var elm_label = DataType("Label",1)
    var elm_name = DataType("Name",1)
    var elm_name2 = DataType("Name",1)
    var elm_control = DataType("Control",1)
    var elm_size = DataType("Size",1)
    var elm_max = DataType("Max",1)
    var elm_note = DataType("Note",1)
    var elm_opr1 = DataType("Ope1",1)
    var elm_opr2 = DataType("Ope2",1)
    var elm_opr3 = DataType("Ope3",1)
    var but_mod = DataType("Ope3",1)
    var but_up = DataType("Up",1)
    var but_down = DataType("Down",1)
    var but_add = DataType("Add",1)
    var but_del = DataType("Del_Elm",1)
    var but_save = DataType("Save_Ret",1)
    var but_list = DataType("Make_List",1)
    var but_detail = DataType("Make_Detail",1)

    var table = TableType("Elements", 1)
    var addTable = TableType("Elements", 1)

	title.getControl(2).setControl(0, 0, 0)
	title.getControl(3).setControl(0, 0, 0)
    page_nm.getControl(2).setControl(7, 16, 16)
    page_nm.getControl(3).setControl(1, 0, 0)
    elm_order.getControl(2).setControl(5,0,0)
    elm_order.getControl(3).setControl(5,0,0)
    elm_order2.getControl(2).setControl(0,0,0)
    elm_order2.getControl(3).setControl(0,0,0)
    elm_label.getControl(2).setControl(7,0,0)
    elm_label.getControl(3).setControl(7,0,0)
    elm_name.getControl(2).setControl(1,0,0)
    elm_name.getControl(3).setControl(1,0,0)
    elm_name2.getControl(2).setControl(7,0,0)
    elm_name2.getControl(3).setControl(7,0,0)
    elm_control.getControl(2).setControl(7,0,0)
    elm_control.getControl(3).setControl(7,0,0)
    elm_size.getControl(2).setControl(2,5,3)
    elm_size.getControl(3).setControl(2,5,3)
    elm_max.getControl(2).setControl(2,5,3)
    elm_max.getControl(3).setControl(2,5,3)
    elm_note.getControl(2).setControl(2,16,16)
    elm_note.getControl(3).setControl(2,16,16)
    elm_opr1.getControl(2).setControl(10,0,0)
    elm_opr1.getControl(3).setControl(10,0,0)
    elm_opr2.getControl(2).setControl(10,0,0)
    elm_opr2.getControl(3).setControl(10,0,0)
    elm_opr3.getControl(2).setControl(10,0,0)
    elm_opr3.getControl(3).setControl(10,0,0)
/*    but_mod.getControl(2).setControl(10,0,0)
    but_mod.getControl(3).setControl(10,0,0)
    but_up.getControl(2).setControl
    but_down.getControl(2).setControl
    but_add.getControl(2).setControl
    but_del.getControl(2).setControl
    but_save.getControl(2).setControl
    but_list.getControl(2).setControl
    but_detail.getControl(2).setControl*/
    elm_control.realValue_fg = false
    page_nm.embedItem = <ADM004:page_nm />
    elm_order.embedItem = <ADM004:elm_order />
    elm_label.embedItem = <ADM004:elm_label />
    elm_name.embedItem = <ADM004:elm_name />
    elm_name2.embedItem = <ADM004:elm_name />
    elm_control.embedItem = <ADM004:elm_control />
    elm_size.embedItem = <ADM004:elm_size />
    elm_max.embedItem = <ADM004:elm_max />
    elm_note.embedItem = <ADM004:elm_note />
    elm_opr1.embedItem = <ADM004:elm_opr1 />
    elm_opr2.embedItem = <ADM004:elm_opr2 />
    elm_opr3.embedItem = <ADM004:elm_opr3 />

    elm_name.realValue_fg = false
    elm_name.getVisualValue={ 
        (lang_id:Int, operation_id:Int, a:String) => { 
          val num = { try { a.toInt } catch { case e:NumberFormatException => -1 }}
          Log.info("ODO:getVisualValue: " + a + " operation_id:" + operation_id)
          DAO_OPERATION_DATA_001.getOperationDataNameById(operation_id, num.toLong)
/*match { 
            case (d:DAO_OPERATION_DATA_001)::Nil => d.data_nm.toString
            case Nil => ""
          }*/
        }
    }
   elm_control.listOptions = DAO_SYSTEM_VALIDATION_001.getSelectList("v_control")
   elm_label.listOptions = DAO_LABEL_001.getSelectListLabel(1,1)
  
   table.add(elm_order, elm_label, elm_name, elm_control, elm_size, elm_max, elm_note)
   addTable.add(elm_order2, elm_label, elm_name2, elm_control, elm_size, elm_max, elm_note)
  
}

object ODO_ADM004 extends ODO_ADM004 {
	val body = Map(
		"title" -> title,
        "page_nm" -> page_nm,
        "table" -> table,
        "addTable" -> addTable

/*    but_mod -> 
    but_up -> 
    but_down -> 
    but_add -> 
    but_del -> 
    but_save -> 
    but_list -> 
    but_detail -> */
	)

    def setOptions(lang_id:Int, operation_id:Int):Unit = {
        body.values.map(_.setOptions(lang_id, operation_id))
    }
}

