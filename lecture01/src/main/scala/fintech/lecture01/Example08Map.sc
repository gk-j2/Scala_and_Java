val capitals = Map(
  "Russia" -> "Moscow",
  "Great Britain" -> "London"
) + ("Nowhere" -> "Nocity")

capitals.keys
capitals.values

capitals("Great Britain")

capitals
  .map(kv => kv._1 * 2 -> kv._2 * 3)
  .filterNot(_._1 == "NowhereNowhere")
  .foreach(println)