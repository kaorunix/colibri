package ni.edu.uca.colibri.dao

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._

class DAO_ORGANIZATION_001 extends KeyedMapper[String, DAO_ORGANIZATION_001] {

	def getSingleton = DAO_ORGANIZATION_001
	def primaryKeyField = organization_nm
	//override def dbIndexes = Index(IndexField(organization_nm)) :: Nil 
	object organization_nm extends MappedStringIndex(this, 16) {
//		override def dbIndexed_? = true
	}
	object organization_full_nm extends MappedString(this, 64)
	object status extends MappedInt(this)
	object delete_fg extends MappedInt(this)
}

object DAO_ORGANIZATION_001 extends DAO_ORGANIZATION_001 with KeyedMetaMapper[String, DAO_ORGANIZATION_001]{
   override def dbTableName = "organization"
   override def fieldOrder = organization_nm :: organization_full_nm :: status :: delete_fg :: Nil
   def add(orgNm: String, fullNm:String) = {
	   S.error("S.error")
	   DAO_ORGANIZATION_001
	   .create
   		.organization_nm(orgNm)
   		.organization_full_nm(fullNm)
   		.status(1)
   		.delete_fg(0)
//   		.save
   }
   def findByOrganizationNm(orgNm:String) = {
	   DAO_ORGANIZATION_001
	   	.findAll(
	      By(DAO_ORGANIZATION_001.organization_nm, orgNm))
   }
   def findBySQLOrganizationNm(orgNm:String) = {
	   DAO_ORGANIZATION_001
	   	.findAll(
	   		BySql("organization_nm = " + orgNm, 
	   				IHaveValidatedThisSQL("kaoru", "2011-03-01")))
   }
   def deleteDeleteFgYes() = {
	   DAO_ORGANIZATION_001
	   	.bulkDelete_!!(
	   		By(DAO_ORGANIZATION_001.delete_fg, 1))
   }
//   def toFormExecution = this.toForm(Full("Execute"),{ _.save})
   def toFormExecution = this.toForm(Full("Execute"),"/AdminPage/ADM001REF")

}
