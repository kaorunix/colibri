package ni.edu.uca.colibri.odo


import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._

class ODO_OPR002 {
    var title= DataType ("Operation_Data",1)
    var operation_id= DataType ("Operation_ID",1)
    var operation_nm= DataType ("Operation_Name",1)
    var description= DataType ("Description", 1)
    var data_order= DataType ("nbsp", 1)
    var data_nm = DataType ("Name", 1)
    var data_type= DataType ("Type", 1)
    var size= DataType ("Size", 1)
    var null_allowed= DataType ("Null_Allowed", 1)
    var ok_button= DataType ("ok_button", 1)
    
    var table = TableType ("table", 1)
    
    operation_nm.getControl(1).setControl(1, 0, 0)
    operation_nm.getControl(2).setControl(2, 16, 16)
    operation_nm.getControl(3).setControl(1, 0, 0)
    operation_nm.getControl(4).setControl(1, 0, 0)
    operation_nm.getControl(5).setControl(1, 0, 0)
    operation_nm.getControl(6).setControl(1, 0, 0)
        
    description.getControl(1).setControl(1, 0, 0)
    description.getControl(2).setControl(2, 50,50)
    description.getControl(3).setControl(1, 0, 0)
    description.getControl(4).setControl(1, 0, 0)
    description.getControl(5).setControl(1, 0, 0)
    description.getControl(6).setControl(1, 0, 0)

    data_order.getControl(2).setControl(5,0,0)
    data_order.getControl(3).setControl(5,0,0)
   
    data_nm.getControl(1).setControl(1, 0, 0)
    data_nm.getControl(2).setControl(2, 16, 16)
    data_nm.getControl(3).setControl(2, 16, 16)
    data_nm.getControl(4).setControl(1, 0, 0)
    data_nm.getControl(5).setControl(1, 0, 0)
    data_nm.getControl(6).setControl(1, 0, 0)

    data_type.getControl(1).setControl(1 ,0, 0)
    data_type.getControl(2).setControl(7 ,0, 0)
    data_type.getControl(3).setControl(7 ,0, 0)
    data_type.getControl(4).setControl(1 ,0, 0)
    data_type.getControl(5).setControl(1 ,0, 0)
    data_type.getControl(6).setControl(1 ,0, 0)

    size.getControl(1).setControl(1, 0, 0)
    size.getControl(2).setControl(2, 16, 16)
    size.getControl(3).setControl(2, 16, 16)
    size.getControl(4).setControl(1, 0, 0)
    size.getControl(5).setControl(1, 0, 0)
    size.getControl(6).setControl(1, 0, 0)
    null_allowed.getControl(1).setControl(1, 0, 0)
    null_allowed.getControl(2).setControl(7, 16, 16)
    null_allowed.getControl(3).setControl(7, 16, 16)
    null_allowed.getControl(4).setControl(1, 0, 0)
    null_allowed.getControl(5).setControl(1, 0, 0)
    null_allowed.getControl(6).setControl(1, 0, 0)


   data_order.embedItem = <OPR002:data_order />
   operation_nm.embedItem= <OPR002:operation_nm />
   description.embedItem = <OPR002:description />
   data_nm.embedItem = <OPR002:data_nm />
   data_type.embedItem = <OPR002:data_type />
   size.embedItem = <OPR002:size />
   null_allowed.embedItem = <OPR002:null_allowed />
   data_type.realValue_fg = false
   null_allowed.realValue_fg = false

   data_type.setValidation("v_type") 
   null_allowed.setValidation("v_null_allow")
   table.add (data_order, data_nm, data_type, size, null_allowed)
   
   
}

   
object ODO_OPR002  extends ODO_OPR002 {
	var body = Map(
        "title" -> title, 
        "operation_id" -> operation_id,
		"operation_nm" -> operation_nm,
		"description" -> description,
        "table" -> table,
		"ok_botton" -> 	ok_button)
        
    def setOptions(lang_id:Int, operation_id:Int):Unit = {
        body.values.map(_.setOptions(lang_id, operation_id))
    }

}
