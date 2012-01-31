package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._
import _root_.net.liftweb.http.provider._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, StandardDBVendor}
import _root_.java.sql.{Connection, DriverManager}
import _root_.ni.edu.uca.colibri.model._
import _root_.ni.edu.uca.colibri.fw._


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor = 
	new StandardDBVendor(Props.get("db.driver") openOr "org.h2.Driver",
			     Props.get("db.url") openOr 
			     "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
			     Props.get("db.user"), Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
    }

    // where to search snippet
    LiftRules.addToPackages("ni.edu.uca.colibri")
    val databases = objectDatabase.database
    Schemifier.schemify(true, Schemifier.infoF _, databases: _*)
    val oprMenu = new DynamicOperation

    val OperationMenu = List(
            Menu(Loc("Operation", List("Operation", "Operation"), "Operation")),
    		Menu(Loc("Column", List("Operation", "Column"), "Column")),
    		Menu(Loc("Ref",	List("Operation", "Ref"), "Ref")),
    		Menu(Loc("Mod", List("Operation", "Mod"), "Mod")))
      

     val menuAdd = OperationMenu:::oprMenu.mkMenu() 


    // Build SiteMap
    def sitemap() = SiteMap(
      Menu("Home") / "index" >> User.AddUserMenusAfter, // Simple menu form
      // Menu with special Link
	  Menu(Loc("Page",
    		List("operation", "Page"), "Page"), (menuAdd: _*))      
      )
//      Menu(Loc("Static", Link(List("static"), true, "/static/index"), 
//	       "Static Content")))

    LiftRules.setSiteMapFunc(() => User.sitemapMutator(sitemap()))

    /*
     * Show the spinny image when an Ajax call starts
     */
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    /*
     * Make the spinny image go away when it ends
     */
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(makeUtf8)

    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    S.addAround(DB.buildLoanWrapper)
  }

  /**
   * Force the request to be UTF-8
   */
  private def makeUtf8(req: HTTPRequest) {
    req.setCharacterEncoding("UTF-8")
  }

/*  def getMenu(): List[Menu] = { 
        val OperationMenu = List(
            Menu(Loc("Operation", List("Operation", "Operation"), "Operation")),
    		Menu(Loc("Columnas", List("Operation", "Columnas"), "Columnas")),
    		Menu(Loc("Ref",	List("Operation", "Ref"), "Ref")),
    		Menu(Loc("Mod", List("Operation", "Mod"), "Mod")))
  }*/
    

//    val elm = Elements.findAll(PreCache(Elements.name))
//    val elm = Elements.findAll(PreCache(Elements.name))
//    elm.map(a => Menu(Loc(a.name, List(a.name), a.name)))

  
}
