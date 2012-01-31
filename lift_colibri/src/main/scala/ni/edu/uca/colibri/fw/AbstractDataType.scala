package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.scala.xml._
import _root_.net.liftweb.http._
import _root_.net.liftweb.common._
import _root_.net.liftweb.common.Logger

abstract class AbstractDataType(var lab_nm: String, var opr_id: Long) { 
	var lang_id: Int = 1  
    var message_nm = ""
    var embedItem = <DIV />


    def copy(): AbstractDataType ={ 
      this
//        var dcopy = new AbstractDataType(lab_nm, opr_id)
//        dcopy
    }

	def toTableElement(lang_id: Int, operation_id: Int) = {
      <tr>
        <th>{toLabel(lang_id, operation_id)}</th>
        <td>{embedItem} </td>
      </tr>
	}

    def toTableElement(lang_id: Int):NodeSeq = { 
        toTableElement(lang_id, 1)
    }

	def toLabel(lang_id: Int) = {
	    DAO_LABEL_001.getLabel(lang_id, lab_nm, 1)
	}
	def toLabel(lang_id: Int, operation_id: Int) = {
	    DAO_LABEL_001.getLabel(lang_id, lab_nm, operation_id)
	}
	def toMessage(lang_id: Int) = {
	    DAO_MESSAGE_001.getMessage(lang_id, message_nm, opr_id)
	}
	def content (operation_id:Int)={
		DAO_OPERATION_DATA_001.getDetail(operation_id)
	}
/*    def copy(): T <: AbstractDataType = { 
        var dcopy = new this
        this
    } */

}
