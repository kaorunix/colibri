package ni.edu.uca.colibri.odo

import ni.edu.uca.colibri.fw._

class ODO_LOG001 {
    var page_name = "LOG001"
	var title = DataType("Login", 1)
	var organization_nm = DataType("Organization", 1)
	var login_nm = DataType("User", 1) 
	var password = DataType("Password", 1)
//    var dataTable = TableType("Data", 1)

	title.getControl(1).setControl(0, 0, 0)
	organization_nm.getControl(1).setControl(0, 0, 0)
    organization_nm.embedItem = <LOG001_1:organization_nm />
	login_nm.getControl(1).setControl(0, 0, 0)
    login_nm.embedItem = <LOG001_1:login_nm />
	password.getControl(1).setControl(0, 0, 0)
	password.embedItem = <LOG001_1:password />
    

}

object ODO_LOG001 extends ODO_LOG001 {
	val body = Map(
		"title" -> title,
		"organization_nm" -> organization_nm,
		"login_nm" -> login_nm,
		"password" -> password
	)
}

