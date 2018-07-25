package co.com.scalatraining.effects

import org.scalatest.FunSuite

import scala.collection.immutable.Seq

class OptionSuite extends FunSuite {

  test("Se debe poder crear un Option con valor"){
    val s = Option{
      1
    }
    assert(s == Some(1))
  }

  test("Se debe poder crear un Option con valor some"){
    val s = Some{
      1
    }
    assert(s == Some(1))
  }

  test("Se debe poder crear un Option para denotar que no hay valor"){
    val s = None
    assert(s == None)
  }

  test("Es inseguro acceder al valor de un Option con get"){
    val s = None
    assertThrows[NoSuchElementException]{
      val r = s.get
    }


  }

  test("Se debe poder hacer pattern match sobre un Option") {
    val lista: Seq[Option[String]] = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre: Option[String] = lista(1)
    var res = ""
    res = nombre match {
      case Some(nom) => nom
      case None => "NONAME"
    }
    assert(res == "NONAME")
  }

  test("Fold en Option"){
    val o = Option(1)

    val res: Int = o.fold{
      10
    }{
      x => x + 20
    }

    assert(res == 21)
  }

  test("Fold en Option manejando null"){
    def foo(a: Int):Option[Int] ={
      //var res = 0
      /*res = a match {
        case a%2 = 0 => Some
        case a%2 !=0 => None      }*/
      if(a%2 == 0){
        Some(a)
      }else{
        None
      }
    }
    val o:Option[Int] = foo(2)

    val res: Int = o.fold{
      10
    }{
      x => x + 20
    }

    assert(res == 22)
  }

  test("Se debe poder saber si un Option tiene valor con isDefined") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)
    assert(nombre.isDefined)
  }

  test("Se debe poder acceder al valor de un Option de forma segura con getOrElse") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(1)
    val res = nombre.getOrElse("NONAME")
    assert(res == "NONAME")
  }

  test("Un Option se debe poder transformar con un map") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)
    val nombreCompleto: Option[String] = nombre.map(s => s + " Felipe")
    assert(nombreCompleto.getOrElse("NONAME") == "Andres Felipe")
  }

  test("Un Option se debe poder transformar con flatMap en otro Option") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)

    val resultado: Option[String] = nombre.flatMap(s => Option(s.toUpperCase))
    resultado.map( s => assert( s == "ANDRES"))
  }

  test("Un Option se debe poder filtrar con una hof con filter") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val option0 = lista(0)
    val option1 = lista(1)
    val res0 = option0.filter(_>10)
    val res1 = option1.filter(_>10)

    assert(res0 == None)
    assert(res1 == None)
  }

  test("Un Option se debe poder filtrar con una hof con filter funcion que cumpla para el 5") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val option0 = lista(0)
    val option1 = lista(1)
    val option2 = lista(0)
    val res0 = option0.filter(_>10)
    val res1 = option1.filter(_>10)
    val res2 = option2.filter(_<10)
    assert(res0 == None)
    assert(res1 == None)
    assert(res2 == Some(5))
  }

  test("for comprehensions en Option") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val s1 = lista(0)
    val s2 = lista(2)

    val resultado = for {
      x <- s1
      y <- s2
    } yield x+y

    assert(resultado == Some(45))
  }

  test("for comprehensions en Option con más de un some") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val s1 = lista(0)
    val s2 = lista(2)
    val s3 = lista(1)

    val resultado = for {
      x <- s1
      y <- s2
      z <- s2
    } yield x+y+z

    assert(resultado == Some(85))
  }

  test("for comprehensions en Option recibe un entero") {
    def foo(a:Int):Option[Int]={
      println(s"Ejecutando foo con ${a}")
      Some(a)
    }

    def bar(b:Int):Option[Int]= {
      println(s"Ejecutando bar con ${b}")
      None
    }


    val resultado = for {
      x <- foo(1)
      a <- foo(2)
      b <- foo(3)
      c <- foo(4)
      d <- foo(5)
      e <- bar(6)
      g <- foo(7)
      h <- bar(8)
      y <- bar(1)
      z <- foo(9)
    } yield x+y+z

    assert(resultado == None)
  }

  test("for comprehensions en Option 3 some") {

    val o1 = Some(1)
    val o2 = Some(2)
    val o3 = Some(3)
    val res = o1.flatMap{x => o2.flatMap(
      y => o3.flatMap( z => Option(x + y +z)))
    }


    assert(res == Some(6))
  }

  test("for comprehesions None en Option") {
    val consultarNombre = Some("Andres")
    val consultarApellido = Some("Estrada")
    val consultarEdad = None
    val consultarSexo = Some("M")

    val resultado = for {
      nom <- consultarNombre
      ape <- consultarApellido
      eda <- consultarEdad
      sex <- consultarSexo
    //} yield (nom+","+ape+","+eda+","+sex)
    } yield (s"$nom $ape, $eda,$sex")

    assert(resultado == None)
  }

  test("for comprehesions None en Option 2") {

    def consultarNombre(dni:String): Option[String] = Some("Felix")
    def consultarApellido(dni:String): Option[String] = Some("Vergara")
    def consultarEdad(dni:String): Option[String] = None
    def consultarSexo(dni:String): Option[String] = Some("M")

    val dni = "8027133"
    val resultado = for {
      nom <- consultarNombre(dni)
      ape <- consultarApellido(dni)
      eda <- consultarEdad(dni)
      sex <- consultarSexo(dni)
    //} yield (nom+","+ape+","+eda+","+sex)
    } yield (s"$nom $ape, $eda,$sex")

    assert(resultado == None)
  }


  /* Tony Morris blog - http://blog.tmorris.net/posts/scalaoption-cheat-sheet*/
  test("Options helps - Tony Morris - flatMap") {
    val b = 1
    //val option: Option[Int] = Some(b)
    val option: Option[Int] = None
    def foo(a: Int): Option[Int] = {
      println(s"Ejecutando foo con ${a}")
      Some(a)
    }
   var res = option match {
    case None => None
    case Some(x) => foo(x)
  }
    val res1 = option.flatMap(x => foo(b))
    assert(res == res1 )
  }

  test("Options helps - Tony Morris - flatten") {
    val b = 1
    //val option: Option[Option[Int]] = Option(Some(b))
    val option: Option[Option[Int]] = None
    var res = option match {
    case None => None
    case Some(Some(x)) => Some(x)
    }
    val res1 = option.flatten
    assert(res == res1 )
  }

  test("Options helps - Tony Morris - map") {
    val b = 1
    val option: Option[Int] = Some(b)
    //val option: Option[Int] = None

    def foo(a: Int): Option[Int] = {
      println(s"Ejecutando foo con ${a}")
      Some(a)
    }
    val res = option match {
      case None => None
      case Some(x) => Some(foo(x))
    }
    val res1 = option.map(x => foo(b))
    assert(res == res1 )
  }


  test("Options helps - Tony Morris - foreach") {
    val b = 1
    var res1 = 0
    val option: Option[Int] = Some(b)
    //val option: Option[Int] = None

    def foo(a: Int): Int = {
      println(s"Ejecutando foo con ${a}")
      a
    }
    val res = option match {
      case None => {}
      case Some(x) => foo(x)
    }
    option.foreach(b=> res1 = b)
    assert(res == res1)
  }

  test("Options helps - Tony Morris - isDefined") {
    val b = 1
    val option: Option[Int] = Some(b)
    //val option: Option[Int] = None


    val res = option match {
      case None => false
      case Some(_) => true
    }
    val res1 = option.isDefined
    assert(res == res1)
  }

  test("Options helps - Tony Morris - isEmpty") {
    val b = 1
    //val option: Option[Int] = Some(b)
    val option: Option[Int] = None


    val res = option match {
      case None => true
      case Some(_) => false
    }
    val res1 = option.isEmpty
    assert(res == res1)
  }

  test("Options helps - Tony Morris - forall") {
    val b = 1
    val option: Option[Int] = Some(b)
    //val option: Option[Int] = None

    def foo(a: Int): Boolean = {
      println(s"Ejecutando foo con ${a}")
      false
    }
    val res = option match {
      case None => true
      case Some(x) => foo(x)
    }
    val res1 = option.forall(x =>foo(b))
    assert(res == res1)
  }

}

