package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.Stack

class StackSuite extends FunSuite  {

  test("Construccion de un Stack"){
      val stack = Stack(1,2,3)
      assert(stack.size==3)
  }

  test("Adicion de un elemento a un stack"){
    val stack = Stack.empty[Int]
    val stack2 = stack :+ 1
    assert(stack == Stack.empty[Int])
    assert(stack2 == Stack(1))
  }

  test("Desapilar un elemento del stack"){
    val stack: Stack[Int] = Stack(1,2,3)
    val stack2 = stack.pop
    assert(stack2 == Stack(2,3))
  }

  test("Apilar un elemento del stack"){
    val stack: Stack[Int] = Stack(1,2,3)
    val stack2 = stack.push(2)
    assert(stack2 == Stack(2,1,2,3))
  }

  test("Apilar sequencias de elementos en el stack"){
    val stack: Stack[Int] = Stack(4,5,6)
    val stack2 = stack.push(3,2,1)
    assert(stack2 == Stack(1,2,3,4,5,6))
  }

  test("Conocer el elemento en la cima del stack"){
    val stack: Stack[Int] = Stack(1,2,3)
    val stack2 = Stack(stack.top)
    assert(stack2 == Stack(1))
  }

  test("Reversar el stack"){
    val stack: Stack[Int] = Stack(1,2,3)
    val stack2 = stack.reverse
    assert(stack2 == Stack(3,2,1))
  }
}
