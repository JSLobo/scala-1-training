package co.com.scalatraining.tuples

import org.scalatest.FunSuite

class TupleSuite  extends FunSuite {

  test("Una tupla se debe poder crear"){
    val tupla = (1, 2,"3", List(1, 2, 3))
    assert(tupla._2 == 2)
    assert(tupla._4.tail.head == 2)
  }
  test("Tuplas reto"){
    val tupla = (List(1, 2, 3), List(2,3,4), List(3,4,5),List(4,5,6),List(5,6,7))
    val tuplaHeads = (tupla._1.head,tupla._2.head,tupla._3.head,tupla._4.head,tupla._5.head)
    assert(tuplaHeads == (1,2,3,4,5))

  }

}
