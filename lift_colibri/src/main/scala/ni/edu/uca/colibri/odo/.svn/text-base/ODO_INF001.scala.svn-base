package ni.edu.uca.colibri.odo


import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._

class ODO_INF001 {
    var title= DataType ("Inf_Data",1)
    var message= DataType ("Message",1)
    var ok_button= DataType ("ok_button", 1)
    
   message.embedItem = <INF001:message />
}

   
object ODO_INF001  extends ODO_INF001 {
	var body = Map(
        "title" -> title, 
        "message" -> message,
		"ok_botton" -> 	ok_button)
}
