/*
 * ODO_ADM001.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ni.edu.uca.colibri.odo
import ni.edu.uca.colibri.fw._
import ni.edu.uca.colibri.dao._

class ODO_ADM001 {

    var title = DataType ("Page_List", 1)
    var operation_id = DataType ("Operation_ID", 1)
    var mode = DataType ("Page_Name", 1)
    var ok_button = DataType ("ok_button", 1)
}

object ODO_ADM001 extends ODO_ADM001 {
    var body = Map(
    	"title" -> title,
        "operation_id" -> operation_id,
        "mode" -> mode,
        "ok_button" -> ok_button)
}
