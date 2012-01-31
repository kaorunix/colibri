package ni.edu.uca.colibri.ido

import ni.edu.uca.colibri.fw._

case class IDO_OPR002 {
	var title= DataType("Operation_Data",1)
	var operation_id= DataType ("Operation_ID",1)
	var operation_name= DataType("Operation_Name",1)
	var description= DataType ("Description",1)
	
	
	operation_name.getControl(1).setControl(0, 0, 0)
	description.getControl(1).setControl(1, 50, 50)
	
}

object IDO_OPR002 extends IDO_OPR002{
	var body= Map(
		"title"->title,	
		"operation_id" -> operation_id,
		"operation_nm" -> operation_name,
		"description" -> description
		
		)
}
