package fintech.lecture.examples

import scala.collection.mutable

object Example18MutableSet {
  // construct
  val set = mutable.Set("one", "two", "three")

  //access
  val elem = set("one") // set is like function

  //updates
  val set1 = set + "four"
  val set2 = set - "one"

  //updates in place
  set += "four"
  set -= "three"
}
