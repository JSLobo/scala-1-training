package co.com.scalatraining.limpieza.historial.laboral

case class Cotizacion(data: Map[Int,List[String]]) {

  def cotizar: Map[Int,List[String]] ={
    var dataTemp = regla1
    regla2
    regla3
    regla4
    regla5
  }

  def regla1:Map[Int,List[String]] = {
    var data1 = data
    var arrayDeIgnorados: Array[Int] = Array()
    var count = 0
    data1.foreach((x) =>
      if((x._2).apply(2) == 0|| (x._2).apply(3) == 0){
        arrayDeIgnorados = arrayDeIgnorados :+ (count+1)
      }
        count = count + 1
    )

    data1 = 
  }
  def regla2:Map[Int,List[String]] = {

  }
  def regla3:Map[Int,List[String]] = {

  }
  def regla4:Map[Int,List[String]] = {

  }
  def regla5:Map[Int,List[String]] = {

  }
}
