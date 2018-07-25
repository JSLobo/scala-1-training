import scala.collection.immutable.Stack

val stack = Stack.empty[Int]
val stack2 = stack :+ 1
  assert(stack == Stack.empty[Int])
  assert(stack2 == Stack(1))

val data: Map[Int,List[String]] = Map(1 -> List("2018/07","S4N","10","1000000"), 2 ->
  List("2018/07","S4N","20","1000000"), 3 ->
  List("2018/08","S4N","30","2000000"))

println(data.get(1))