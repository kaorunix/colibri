package ni.edu.uca.colibri.odo


import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._

class ODO_OPR001 {
    var title= DataType ("Operation",1)
    var operation_id= DataType("nbsp",1)
    var operation_nm= DataType ("Operation_Name", 1)
    var status= DataType ("Status", 1)
    var data_quantity= DataType ("Data_Quantity",1)
    var organization_nm= DataType ("Organization",1)
    var login_nm= DataType ("User",1)
    var update_dt= DataType ("DateTime",1)
    var mode= DataType ("Operation_Name",1)
    var ok_button= DataType ("ok_button", 1)

    var table = TableType ("table", 1)
    
    
    operation_id.getControl(1).setControl(4, 0, 0)
    operation_id.getControl(2).setControl(4, 0, 0)
    operation_id.getControl(3).setControl(4, 0, 0)
    operation_id.getControl(4).setControl(4, 0, 0)
    operation_id.getControl(5).setControl(4, 0, 0)
    operation_id.getControl(6).setControl(4, 0, 0)
        
    operation_nm.getControl(1).setControl(1, 0, 0)
    operation_nm.getControl(2).setControl(1, 0, 0)
    operation_nm.getControl(3).setControl(1, 0, 0)
    operation_nm.getControl(4).setControl(1, 0, 0)
    operation_nm.getControl(5).setControl(1, 0, 0)
    operation_nm.getControl(6).setControl(1, 0, 0)
    
    status.getControl(1).setControl(1,0,0)
    status.getControl(2).setControl(1,0,0)
    status.getControl(3).setControl(1,0,0)
    status.getControl(4).setControl(1,0,0)
    status.getControl(5).setControl(1,0,0)
    status.getControl(6).setControl(1,0,0)
    
    data_quantity.getControl(1).setControl(1,0,0)
    data_quantity.getControl(2).setControl(1,0,0)
    data_quantity.getControl(3).setControl(1,0,0)
    data_quantity.getControl(4).setControl(1,0,0)
    data_quantity.getControl(5).setControl(1,0,0)
    data_quantity.getControl(6).setControl(1,0,0)
    
    organization_nm.getControl(1).setControl(1,0,0)
    organization_nm.getControl(2).setControl(1,0,0)
    organization_nm.getControl(3).setControl(1,0,0)
    organization_nm.getControl(4).setControl(1,0,0)
    organization_nm.getControl(5).setControl(1,0,0)
    organization_nm.getControl(6).setControl(1,0,0)
    
    login_nm.getControl(1).setControl(1,0,0)
    login_nm.getControl(2).setControl(1,0,0)
    login_nm.getControl(3).setControl(1,0,0)
    login_nm.getControl(4).setControl(1,0,0)
    login_nm.getControl(5).setControl(1,0,0)
    login_nm.getControl(6).setControl(1,0,0)
    
    update_dt.getControl(1).setControl(1,0,0)
    update_dt.getControl(2).setControl(1,0,0)
    update_dt.getControl(3).setControl(1,0,0)
    update_dt.getControl(4).setControl(1,0,0)
    update_dt.getControl(5).setControl(1,0,0)
    update_dt.getControl(6).setControl(1,0,0)
    
    operation_id.embedItem = <OPR001:operation_id />
    operation_nm.embedItem= <OPR001:operation_nm />
    status.embedItem= <OPR001:status />
    data_quantity.embedItem= <OPR001:data_quantity />
    organization_nm.embedItem= <OPR001:organization />
    login_nm.embedItem= <OPR001:login_nm />
    update_dt.embedItem= <OPR001:date />
    status.realValue_fg = false

    status.setValidation("v_status")
    table.add (operation_id, operation_nm, status, data_quantity, organization_nm, login_nm, update_dt)
   
}

   
object ODO_OPR001  extends ODO_OPR001 {
	var body = Map(
        "title" -> title, 
        "table" -> table,
        "mode" -> mode,
		"ok_botton" -> 	ok_button)
        

}
