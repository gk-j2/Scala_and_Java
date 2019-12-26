val elements =
  "fire" :: List("water","earth","air")

elements.size
elements(1)

var newElements =
  List("fire","water","earth","air")

newElements =
  newElements.updated(2, "darkness")

newElements
  .map(x => x * 2)
  .filterNot(_ == "darknessdarkness")
  .foreach(println)