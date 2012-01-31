package ni.edu.uca.colibri.odo


import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._

class ODO_ADM006 {
  /**
   * 0  NONE :No va aparecer
   * 1	LABEL
   * 2	TEXT
   * 3	PASS
   * 4	RADIO
   * 5	CHECKBOX
   * 6	RADIO2OP
   * 7	SELECT
   * 8	SELECT2OP
   * 9	SELECTLIST
   * 10	HIDDEN
   * 11	BUTTON
   */
    var title= DataType ("DetailPage",1)
    var page_nm= DataType ("nbsp",1)
    var panel_id= DataType ("nbsp",1)
    var panel_order= DataType ("nbsp",1)
    var contents = TableType("Contents", 1)

    var con_order= DataType ("nbsp",1)
    var con_order2= DataType ("nbsp",1)
    var con_element= DataType ("Lab_DataNm", 1)
    var con_element2= DataType ("Lab_DataNm", 1)
    var con_control = DataType ("Control", 1)
    var con_control2 = DataType ("Control", 1)
    var con_id = DataType ("nbsp", 1)
    var con_mode= DataType ("Mode", 1)

    var con_label= DataType ("nbsp", 1)
    var con_data_nm= DataType ("nbsp", 1)
  // テーブルの要素用
    var table = ListTableType ("Contents", 1)
  // テーブル要素追加用
    var addTable = ListTableType ("Contents", 1)

    panel_id.getControl(1).setControl(10, 0, 0)
    panel_id.getControl(2).setControl(10, 0, 0)
    panel_id.getControl(3).setControl(10, 0, 0)
    panel_id.getControl(4).setControl(10, 0, 0)
    panel_id.getControl(5).setControl(10, 0, 0)
    panel_id.getControl(6).setControl(10, 0, 0)
    
    panel_order.getControl(1).setControl(0, 0, 0)
    panel_order.getControl(2).setControl(5, 0, 0)
    panel_order.getControl(3).setControl(5, 0, 0)
    panel_order.getControl(4).setControl(0, 0, 0)
    panel_order.getControl(5).setControl(0, 0, 0)
    panel_order.getControl(6).setControl(0, 0, 0)

    con_element.getControl(1).setControl(1, 0, 0)
    con_element.getControl(2).setControl(1, 0, 0)
    con_element.getControl(3).setControl(1, 0, 0)
    con_element.getControl(4).setControl(1, 0, 0)
    con_element.getControl(5).setControl(1, 0, 0)
    con_element.getControl(6).setControl(1, 0, 0)

    con_element2.getControl(1).setControl(1, 0, 0)
    con_element2.getControl(2).setControl(7, 0, 0)
    con_element2.getControl(3).setControl(7, 0, 0)
    con_element2.getControl(4).setControl(1, 0, 0)
    con_element2.getControl(5).setControl(1, 0, 0)
    con_element2.getControl(6).setControl(1, 0, 0)

    con_order.getControl(1).setControl(0, 0, 0)
    con_order.getControl(2).setControl(5, 0, 0)
    con_order.getControl(3).setControl(5, 0, 0)
    con_order.getControl(4).setControl(0, 0, 0)
    con_order.getControl(5).setControl(0, 0, 0)
    con_order.getControl(6).setControl(0, 0, 0)

    con_order2.getControl(1).setControl(0, 0, 0)
    con_order2.getControl(2).setControl(0, 0, 0)
    con_order2.getControl(3).setControl(0, 0, 0)
    con_order2.getControl(4).setControl(0, 0, 0)
    con_order2.getControl(5).setControl(0, 0, 0)
    con_order2.getControl(6).setControl(0, 0, 0)

    con_control.getControl(1).setControl(1, 0, 0)
    con_control.getControl(2).setControl(7, 0, 0)
    con_control.getControl(3).setControl(7, 0, 0)
    con_control.getControl(4).setControl(1, 0, 0)
    con_control.getControl(5).setControl(1, 0, 0)
    con_control.getControl(6).setControl(1, 0, 0)

    con_mode.getControl(1).setControl(1, 0, 0)
    con_mode.getControl(2).setControl(7, 0, 0)
    con_mode.getControl(3).setControl(7, 0, 0)
    con_mode.getControl(4).setControl(1, 0, 0)
    con_mode.getControl(5).setControl(1, 0, 0)
    con_mode.getControl(6).setControl(1, 0, 0)

    con_id.getControl(1).setControl(10, 0, 0)
    con_id.getControl(2).setControl(10, 0, 0)
    con_id.getControl(3).setControl(10, 0, 0)
    con_id.getControl(4).setControl(10, 0, 0)
    con_id.getControl(5).setControl(10, 0, 0)
    con_id.getControl(6).setControl(10, 0, 0)

    panel_order.embedItem = <ADM006:panel_order />
    table.embedItem= <ADM006:contents />
    addTable.embedItem= <ADM006:contents />
    con_order.embedItem = <ADM006:con_order />
    con_order2.embedItem = <ADM006:con_order2 />
    con_element.embedItem = <ADM006:con_element />
    con_element2.embedItem = <ADM006:con_element2 />
    con_control.embedItem = <ADM006:con_control />
    con_mode.embedItem = <ADM006:con_mode />
    con_id.embedItem = <ADM006:con_id />

//    data_type.realValue_fg = false
    con_control.realValue_fg = false
    con_mode.realValue_fg = false
//    con_element2.realValue_fg = false

    con_control.setValidation("v_control")
    con_mode.setValidation("v_mode")
    table.initList(List(con_order, con_element, con_control, con_mode, con_id))
    addTable.initList(List(con_order2, con_element2, con_control, con_mode, con_id))
    addTable.addList(List(con_order2, con_element2, con_control, con_mode, con_id))
    contents.add (panel_order, table, panel_id)
   
   
}

   
object ODO_ADM006  extends ODO_ADM006 {
	var body = Map(
        "title" -> title, 
        "page_nm" -> page_nm,
        "contents" -> contents,
        "addTable" -> addTable
		)
        
    def setOptions(lang_id:Int, operation_id:Int):Unit = {
        body.values.map(_.setOptions(lang_id, operation_id))
    }

}
