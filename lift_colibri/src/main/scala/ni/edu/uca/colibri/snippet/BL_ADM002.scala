/*
 * BL_ADM002.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



package ni.edu.uca.colibri.snippet
import ni.edu.uca.colibri._


import _root_.scala.xml.{NodeSeq, Text}
import model._
import net.liftweb._
import http._
import SHtml._
import S._
import js._
import JsCmds._
import mapper._
import util._
import Helpers._
import scala.xml.{NodeSeq, Text}
import dao._
import net.liftweb.common.Logger
import _root_.java.util.Date
import ido._
import odo._
import fw._
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.java.util.Date
import ni.edu.uca.colibri.lib._

class BL_ADM002 {
    
    object Log extends Logger
    
    var odo = ODO_ADM002
    var lang_id = 3
    var operation_id = Session.operation_idVar.get.toInt
    var mode = Session.modeVar.get.toInt
    
    def title = {
        <h2>{odo.body("title").toLabel(lang_id)}</h2>
    }
    
    def table = {
        <table>
        {odo.body("elementsTable") match {case t:TableType => t.mkTableTitle(lang_id)}}

        <lift:BL_ADM002.listPages>
            {odo.body("elementsTable") match {case t:TableType => t.mkTableEmbed()}}
        </lift:BL_ADM002.listPages>
        </table>
    }
    def display(form: NodeSeq) = {
        <lift:BL_ADM002.execute form = "post" multipart = "true">
            {table}  
            {transaction}
        </lift:BL_ADM002.execute>
    }

    def transaction(): NodeSeq = {
        <table>
            <tr>
                <td><ADM002:label><button>LABEL</button></ADM002:label></td>
                <td><ADM002:create><button>CREATE</button></ADM002:create></td>
            </tr>
        </table>
    }

    def execute(form: NodeSeq): NodeSeq = {
//        Para el bind se necesita a la izquierda el nombre de los campos de la tabla y a la derecha el nombre en ODO
    	def doBind(form: NodeSeq) = {
            def submitLabel(): NodeSeq = {
                mode match {
                    case 1|2|3|6 => SHtml.submit("OK", exit)
                    case _ => <DIV />
                }

            }
            def submitCreate(): NodeSeq = {
                mode match {
                    case 1 => SHtml.submit("Next", next)
                    case 2 => SHtml.submit("Create", create)
                    case 3 => SHtml.submit("Modify", modify)
                    case 6 => SHtml.submit("Approve", approve)
                    case _ => <DIV />
                }
            }
            bind ("ADM002", form,
                  "label" -> submitLabel,
                  "create" -> submitCreate)
        }
        doBind(form)
    }

    def copyPage()={ }
    def next()={ }
    def create() = {
//        Aqui se puede agregar la opcion para crear New Page
        list.map(a =>{
                if(a.elements(0).checked == true){
                    var id = a.elements(0).getValue.toString.toInt
                    Session.operation_idVar(operation_id)
                    Session.page_idVar(id)
                    Session.modeVar(mode)
                    if (id != 0) DAO_PAGE_001.copyPage(id)
                    S.redirectTo("/AdminPage/ADM004")
                }
            })
    }
    def approve() = {
        list.map(a =>{
                if (a.elements(0).checked == true){
                    var id = a.elements(0).getValue.toString.toInt
                    DAO_PAGE_001.updatePage("",id, mode)
                    Session.operation_idVar(operation_id)
                    Session.page_idVar(id)
                    Session.modeVar(mode)
                    S.redirectTo("/SYS/INF001")
                }
            })
    }
    def modify() = {
        list.map(a =>{
                if (a.elements(0).checked == true){
                    var id = a.elements(0).getValue.toString.toInt
                    Session.operation_idVar(operation_id)
                    Session.page_idVar(id)
                    Session.modeVar(mode)
                    S.redirectTo("/AdminPage/ADM004")
                }
            })
    }
    def exit() = {
        S.redirectTo("/AdminPage/ADM001")
    }
    var list = {
        def convert(d:DAO_PAGE_001, o:TableType): TableType = {
            Log.info("mkList convert:DAO_PAGE_001:" + d.toString + "TableType.elements:" + o.elements.toString)
            o.elements(0).setValue(d.id)
            o.elements(1).setValue(d.page_nm)
            o.elements(2).setValue(DAO_PAGE_001.count_elem_num(d.id).toString)
            o.elements(3).setValue(d.status)
            o.elements(4).setValue(d.login_nm)
            o.elements(5).setValue(d.organization_nm)
            o.elements(6).setValue(d.update_dt)
            Log.info("PAGE NAME "+d.page_nm+" STATUS "+d.status+" TABLETYPE "+o.toString)
            o
        }
        val search:List[DAO_PAGE_001] = {
            val opr = DAO_OPERATION_001.getOperation(operation_id)
            opr match {
                case (a:DAO_OPERATION_001)::Nil =>  {

                	var pg = DAO_PAGE_001.getPageByOperation(operation_id, mode)
                	Log.info("OPERATION = " +a.toString)
                	Log.info("PAGE = "+ pg.toString)
                    if (mode == 2) { 
                      var newPage = DAO_PAGE_001
                      newPage.page_nm("Crear Nueva Pagina")
//                      newPage.id(0)
                      //newPage.status()
                      pg=newPage::pg
                    }

                	pg
                }
                case _ => S.error("NO PAGES FOUND"); Nil
                
            }
        }
        
        search.map(a => convert(a, odo.body("elementsTable") match {case t:TableType => t.copy}))
    }
    def applyForm(form: NodeSeq, d:TableType) = {
        bind("ADM002", form,
             "radiobutton" -> d(0).toForm(mode.toInt),
             "elm_pagename" -> d(1).toForm(mode.toInt),
             "elm_datanum" -> d(2).toForm(mode.toInt),
             "elm_status" -> d(3).toForm(mode.toInt),
             "login_nm" -> d(4).toForm(mode.toInt),
             "org_nm" -> d(5).toForm(mode.toInt),
             "update_dt" -> d(6).toForm(mode.toInt))
        
    }
    def listPages(form: NodeSeq) = {
    	list.flatMap(d => applyForm(form, d))
    	
    }

}
