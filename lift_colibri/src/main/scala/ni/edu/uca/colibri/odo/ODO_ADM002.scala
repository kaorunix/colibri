package ni.edu.uca.colibri.odo

import ni.edu.uca.colibri.fw._

class ODO_ADM002 {
	
	var page_name = "ADM002"
	var title = DataType("Page_List",1)
	
	var radiobutton = DataType("nbsp",1)
	var elm_pagename = DataType("Page_Name",1)
	var elm_datanum = DataType("Data_Quantity",1)
	var elm_category = DataType("Category",1)
	var elm_status = DataType("Status",1)
    var organization_nm= DataType ("Organization",1)
    var login_nm= DataType ("User",1)
    var update_dt= DataType ("DateTime",1)
	var but_message = DataType("Labels_Messages",1)
	var but_createpage = DataType("Create_Page",1)
	
	var elementsTable = TableType("Pages",1)
	
	title.getControl(1).setControl(1,0,0)
	title.getControl(2).setControl(1,0,0)
	title.getControl(3).setControl(1,0,0)
	title.getControl(4).setControl(1,0,0)
	title.getControl(6).setControl(1,0,0)
	radiobutton.getControl(1).setControl(4,0,0)
	radiobutton.getControl(2).setControl(4,0,0)
	radiobutton.getControl(3).setControl(4,0,0)
	radiobutton.getControl(4).setControl(4,0,0)
	radiobutton.getControl(6).setControl(4,0,0)
	elm_pagename.getControl(1).setControl(1,0,0)
	elm_pagename.getControl(2).setControl(1,0,0)
	elm_pagename.getControl(3).setControl(1,0,0)
	elm_pagename.getControl(4).setControl(1,0,0)
	elm_pagename.getControl(6).setControl(1,0,0)
	elm_datanum.getControl(1).setControl(1,5,3)
	elm_datanum.getControl(2).setControl(1,5,3)
	elm_datanum.getControl(3).setControl(1,5,3)
	elm_datanum.getControl(4).setControl(1,5,3)
	elm_datanum.getControl(6).setControl(1,5,3)	
	elm_category.getControl(1).setControl(1,0,0)
	elm_category.getControl(2).setControl(1,0,0)
	elm_category.getControl(3).setControl(1,0,0)
	elm_category.getControl(4).setControl(1,0,0)
	elm_category.getControl(6).setControl(1,0,0)
	elm_status.getControl(1).setControl(1,0,0)
	elm_status.getControl(2).setControl(1,0,0)
	elm_status.getControl(3).setControl(1,0,0)
	elm_status.getControl(4).setControl(1,0,0)
	elm_status.getControl(6).setControl(1,0,0)

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


/* 	but_message.getControl(1).setControl()
  	but_message.getControl(2).setControl()
	but_message.getControl(3).setControl() 
	but_message.getControl(4).setControl()
	but_message.getControl(6).setControl()
	but_createpage.getControl(1).setControl()
	but_createpage.getControl(2).setControl()
	but_createpage.getControl(3).setControl()
	but_createpage.getControl(4).setControl()
	but_createpage.getControl(6).setControl()
*/
	title.embedItem = <ADM002:title />
	radiobutton.embedItem = <ADM002:radiobutton />
	elm_pagename.embedItem = <ADM002:elm_pagename />
	elm_datanum.embedItem = <ADM002:elm_datanum />
//	elm_category.embedItem = <ADM002:elm_category />
	elm_status.embedItem = <ADM002:elm_status />
    organization_nm.embedItem= <ADM002:org_nm />
    login_nm.embedItem= <ADM002:login_nm />
    update_dt.embedItem= <ADM002:update_dt />
	
	elementsTable.add(radiobutton, elm_pagename, elm_datanum, elm_status, organization_nm, login_nm, update_dt)
//	elementsTable.add(elm_pagename, elm_status)

}
object ODO_ADM002 extends ODO_ADM002 {
		
		val body = Map (
			
			"title" -> title,
//			"radiobutton" -> radiobutton,
			"elm_pagename" -> elm_pagename,
//			"elm_datanum" -> elm_datanum,
//			"elm_category" -> elm_category,
			"elm_status" -> elm_status,
			"elementsTable" -> elementsTable)
		/*	but_message ->
		 	but_createpage ->
		 	
		*/
}
