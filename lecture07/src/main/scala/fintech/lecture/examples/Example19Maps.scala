package fintech.lecture.examples

object Example19Maps extends App {
  val list = List(1, 2, 3, 4, 5, 6, 7)
  val map = Map(1 -> "one", 2 -> "two")

  //f == Int => String
  println(
    list.map(x => (x * 2).toString)
  )
  //f == (Int,String) => (String,String)
  println(
    map.map(kv => (kv._1 * 2).toString -> kv._2.reverse)
  )
  //f == (Int,String) => String
  println(
    map.map(kv => (kv._1 * 2).toString)
  )


  //f == Int => String
  println(
    list.flatMap(x => List((x * 2).toString, (x * 3).toString))
  )
  //f == (Int,String) => (String,String)
  println(
    map.flatMap(kv => Map((kv._1 * 2).toString -> kv._2.reverse, (kv._1 * 3).toString -> kv._2.reverse))
  )
  //f == (Int,String) => String
  println(
    map.flatMap(kv => List((kv._1 * 2).toString, (kv._1 * 3).toString))
  )
}