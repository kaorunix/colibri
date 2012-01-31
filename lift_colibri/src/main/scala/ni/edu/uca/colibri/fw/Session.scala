package ni.edu.uca.colibri.fw

import _root_.net.liftweb.http._

object Session {
	object operation_idVar extends SessionVar (S.param("operation_id").map(_.toLong) openOr 0L)
	object page_idVar extends SessionVar (S.param("page_id").map(_.toLong) openOr 0L)
	object modeVar extends SessionVar (S.param("mode").map(_.toInt) openOr 0)
	object messageVar extends SessionVar (S.param("message") openOr 0)
	object panelIdVar extends SessionVar (S.param("panel_id") openOr 0)
}
