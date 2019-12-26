package fintech.lecture.examples

import scala.collection.mutable

object Example17MutableMap {
  // construct
  val map = mutable.Map("one" -> 1, "two" -> 2, "three" -> 3)

  //access
  val elem = map("one") // map is like function // this method is unsafe
  val elemOpt = map.get("one") // this one is safe

  //updates
  val map1 = map + ("four" -> 4)
  val map2 = map - "one"

  //update in place
  map("one") = 42
  map += "two" -> 43
  map -= "three"
}
