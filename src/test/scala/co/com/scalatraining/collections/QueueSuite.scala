package co.com.scalatraining.collections

import org.scalatest.FunSuite

import scala.collection.immutable.{Queue, Stack}

class QueueSuite extends FunSuite  {
  test("Construccion de una cola"){
    val queue = Queue(1,2,3)
    assert(queue.size==3)
  }

  test("Encolar un elemento"){
    val queue = Queue.empty[Int]
    val queue2 = queue.enqueue(1)
    val queue3 = queue2.enqueue(2)
    assert(queue == Queue.empty[Int])
    assert(queue2 == Queue(1))
    assert(queue3 == Queue(1,2))
  }

  test("Desencolar elemento"){
    val queue: Queue[Int] = Queue(1,2,3)
    val queue2 = (queue.dequeue)._2
    assert(queue2 == Queue(2,3))
  }

  test("Obtener primer elemento de la cola"){
    val queue: Queue[Int] = Queue(1,2,3)
    assert(queue.head == 1)
  }

  test("Obtener ultimo elemento de lo cola"){
    val queue: Queue[Int] = Queue(1,2,3)
    assert(queue.last == 3)
  }


  test("Reversar cola"){
    val queue: Queue[Int] = Queue(1,2,3)
    val queue2 = queue.reverse
    assert(queue2 == Queue(3,2,1))
  }
}
