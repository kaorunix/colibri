package ni.edu.uca.colibri.ido

import ni.edu.uca.colibri.fw._

class IDO_LOG001 {
	var title = DataType("login_title", 1)
	var organization_nm = DataType("organization_nm", 1)
	var login_nm = DataType("login_nm", 1) 
	var password = DataType("password", 1)

	title.getControl(1).setControl(0, 0, 0)
	organization_nm.getControl(1).setControl(0, 0, 0)
	login_nm.getControl(1).setControl(0, 0, 0)
	password.getControl(1).setControl(0, 0, 0)
}

object IDO_LOG001 extends IDO_LOG001 {
	val body = Map(
	"title" -> title,
	"organization_nm" -> organization_nm,
	"login_nm" -> login_nm,
	"password" -> password)
}
