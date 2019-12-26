package fintech.lecture03.examples

object Example17ObjectCompanion extends App {
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

  class CannibalCow extends Animal {
    override type AcceptableFood = Beef

    override def eat(food: Beef): Unit =
      println("CannibalCow eats another cow")
  }


  object Animal {
    def cow: Cow = new Cow
    def cannibal: CannibalCow = new CannibalCow


    def apply() = new Cow
  }

  Animal().eat(new Grass)
  Animal.cow.eat(new Grass)
  Animal.cannibal.eat(new Beef)
}

