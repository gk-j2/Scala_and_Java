package fintech.lecture01

object Example12CaseClass {

  sealed trait Animal
  case class Cat(name: String, tailLength: Double) extends Animal
  case class Elephant(name: String, tailLength: Double, trunkLength: Double) extends Animal

  val cat1 = Cat("Garfld", 1.5)
  val cat2 = cat1.copy(name = "Dlfrag")

  cat1 == cat2

  (cat1: Animal) match {
    case Cat(name, tailLength) =>
      println(s"i am cat $name with tail $tailLength")
    case Elephant(name, tailLength, trunkLength) =>
      println(s"i am elephant $name with tail $tailLength and trunk $trunkLength")
  }
}
