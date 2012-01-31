package ni.edu.uca.colibri.fw


import _root_.java.sql._
import _root_.ni.edu.uca.colibri.model._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.org.postgresql._

object DBVendor extends ConnectionManager{
	Class.forName("org.postgresql.Driver")
	def newConnection(name: ConnectionIdentifier) = {
		try {
			Full(DriverManager.getConnection(
					"jdbc:postgresql://colibri.uxlab.net/form",
//					"jdbc:postgresql://192.168.1.27/form",
//					"jdbc:postgresql://localhost/form",
//					"tmp_kaoru", "uca2011"))
					"form", "password"))
		} catch {
			case e: Exception => e.printStackTrace; Empty
		}
	}
	def releaseConnection(conn: Connection) {
		conn.close
	}

}
