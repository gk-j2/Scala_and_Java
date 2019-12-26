package fintech.lecture03.examples

object Example15ClassAbstractTypeAlias extends App {

  abstract class Animal {
    type AcceptableFood <: Food

    def eat(food: AcceptableFood): Unit
  }

  class Food
  class Grass extends Food
  class Beef extends Food

  class Cow extends Animal {
    override type AcceptableFood = Grass

    override def eat(food: Grass): Unit =
      println("Cow eats grass")
  }

  val cow = new Cow
  val acceptableFood: cow.AcceptableFood = new Grass

  cow.eat(acceptableFood)
  cow.eat(new Grass)

//  cow.eat(new Beef)

}
