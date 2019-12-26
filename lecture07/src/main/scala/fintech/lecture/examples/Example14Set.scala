package fintech.lecture.examples

object Example14Set {
  // construct
  val set = Set("one", "two", "three")

  //access
  val elem = set("one") // set is like function

  //updates
  val set1 = set + "four"
  val set2 = set - "one"
}
