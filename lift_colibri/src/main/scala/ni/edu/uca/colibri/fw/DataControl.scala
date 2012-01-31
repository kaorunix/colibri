package ni.edu.uca.colibri.fw

import ni.edu.uca.colibri.dao._
import _root_.net.liftweb.mapper._

case class DataControl (val operation_id: Long, val label_nm: List[String]){
    def this(operation_id:Long) ={ 
      this(operation_id, Nil)
    }
    var data :List[DataType]=Nil
    val dao = DAO_DATA_CONTROL_001.findAll(By(DAO_DATA_CONTROL_001.operation_id, operation_id)).map(a =>
            DAO_TINY_TYPE_001.findAll(By(DAO_TINY_TYPE_001.data_id, a.id), 
                                      OrderBy(DAO_TINY_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> TinyType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap ++
            DAO_SMALL_TYPE_001.findAll(By(DAO_SMALL_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_SMALL_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> SmallType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap ++
            DAO_MIDIUM_TYPE_001.findAll(By(DAO_MIDIUM_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_MIDIUM_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> MidiumType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap ++
            DAO_LARGE_TYPE_001.findAll(By(DAO_LARGE_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_LARGE_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> LargeType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap ++
            DAO_INT_TYPE_001.findAll(By(DAO_INT_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_INT_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> IntType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap ++
            DAO_NUMERIC_TYPE_001.findAll(By(DAO_NUMERIC_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_NUMERIC_TYPE_001.order, Ascending))
                .map(e => (e.order.toInt -> NumberType(label_nm(e.order.toInt), operation_id).setValue(e.value.toString))).toMap

/*            DAO_DATE_TYPE_001.findAll(By(DAO_DATE_TYPE_001.data_id, a.id),
                                      OrderBy(DAO_DATE_TYPE_001.order, Ascending))
                .map(e => Map(e.order -> DateType(label_nm(e.order), operation_id).setValue(e.value)))*/
//            DAO_FILE_001.findAll(DAO_SMALL_001.data_ida, a.id)
//            DAO_SMALL_001.findAll(DAO_SMALL_001.data_ida, a.id)
        )


    def getData():List[Seq[Any]] = { 
//    def getData[T <: DataType]():List[Seq[T]] = { 
      val c = label_nm.size
      //ret:List[T] //= Nil
      dao.map (b => for { i <- (1 to c)  } yield b.toMap.get(i) match {
        case Some(a) => a
        case _ => Nil
      })
    }

/*    def getNewData():[T <: DataType]():List[Seq[T]] = { 
      Null
    }*/


    def mkData():Unit={ 
      val dc = DAO_DATA_CONTROL_001.create
//operation_id(operation_id)
    }
//    apply(data_nm:String)(value:Any)

//    def save()

}
	
	
