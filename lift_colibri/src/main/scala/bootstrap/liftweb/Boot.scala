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
import _root_.ni.edu.uca.colibri.dao._
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
    Schemifier.schemify(true, Schemifier.infoF _, User, 
                        DAO_CONTROL_001, DAO_LABEL_001, DAO_LANGUAGE_001, 
                        DAO_LOGIN_001, DAO_LOGIN_USER_001, 
                        DAO_OPERATION_001, DAO_OPERATION_DATA_001, 
                        DAO_ORGANIZATION_001,DAO_SYSTEM_VALIDATION_001,
                        DAO_MESSAGE_001, DAO_PAGE_001, DAO_PAGE_ELEMENTS_001,
                        DAO_PANEL_001, DAO_TABLE_CONTENTS_001
                      )

    val OperationMenu = List(Menu(Loc("OprRef", List("Operation", "OPR001REF"), "Ref")),
    				Menu(Loc("OprAdd", List("Operation", "OPR002ADD"), "Add")),
    				Menu(Loc("OprEdit",	List("Operation", "OPR001MOD"), "Mod")),
    				Menu(Loc("OprDel", List("Operation", "OPR001DEL"), "Del")),
    				Menu(Loc("OprUndo",	List("Operation", "OPR001UNDO"), "Undo")),
    				Menu(Loc("OprApp", List("Operation", "OPR001APP"), "App")))

    val AdminPageMenu = List(Menu(Loc("AdmRef", List("AdminPage", "ADM001REF"), "Ref")),
    				Menu(Loc("AdmAdd", List("AdminPage", "ADM001ADD"), "Add")),
    				Menu(Loc("AdmEdit",	List("AdminPage", "ADM001MOD"), "Mod")),
    				Menu(Loc("AdmDel", List("AdminPage", "ADM001DEL"), "Del")),
    				Menu(Loc("AdmUndo",	List("AdminPage", "ADM001UNDO"), "Undo")),
    				Menu(Loc("AdmApp", List("AdminPage", "ADM001APP"), "App")),
    				Menu(Loc("AdmList", List("AdminPage", "ADM002"), "Page List")))

    val GenerateMenu = List(Menu(Loc("GenRef", List("Generate", "GEN001REF"), "Ref")),
    				Menu(Loc("GenCre", List("Generate", "GEN002GEN"), "Gen")))

    				
    def entries = List(
    		Menu(Loc("Home",
    				List("index"), "Home")),
    		Menu(Loc("Operation",
    				List("Operation", "OPR001"), "Operation"), (OperationMenu: _*)),
    		Menu(Loc("AdminPage",
    				List("AdminPage", "ADM001"), "AdminPage"), (AdminPageMenu: _*)),
    		Menu(Loc("Generate",
    				List("Generate", "GEN001"), "Generate"), (GenerateMenu: _*)),
    		Menu(Loc("Logout",
    				List("Logout", "LOG001"), "Logout")),
    		Menu(Loc("Information",
    				List("SYS", "INF001"), "INF001", Hidden)),
                Menu(Loc("ADM002",
                                List("AdminPage", "ADM002ADD"), "ADM002ADD", Hidden)),
    		Menu(Loc("OPR002",
    				List("Operation", "OPR002"), "OPR002", Hidden)),
    		Menu(Loc("ADM004",
    				List("AdminPage", "ADM004"), "ADM004", Hidden)),
    		Menu(Loc("ADM005",
    				List("AdminPage", "ADM005"), "ADM005", Hidden)),
    		Menu(Loc("ADM006",
    				List("AdminPage", "ADM006"), "ADM006", Hidden)))

    		// Build SiteMap
    LiftRules.setSiteMap(SiteMap(entries:_*))
    def sitemap() = SiteMap(
      Menu("Home") / "index" >> User.AddUserMenusAfter, // Simple menu form
      // Menu with special Link
      Menu(Loc("Static", Link(List("static"), true, "/static/index"), 
	       "Static Content")))

//    LiftRules.setSiteMapFunc(() => User.sitemapMutator(sitemap()))

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
}
