package fintech.lecture03.examples

object Example18ObjectCompanionAndPrivate extends App {
  abstract class Animal {
    type AcceptableFood <: Food

    def eat(food: AcceptableFood): Unit
  }

  class Food
  class Grass extends Food
  class Beef extends Food

  class Cow private (name: String) extends Animal {
    override type AcceptableFood = Grass

    override def eat(food: Grass): Unit =
      println("Cow eats grass")
  }

  class CannibalCow private (name: String) extends Animal {
    override type AcceptableFood = Beef

    override def eat(food: Beef): Unit =
      println("CannibalCow eats another cow")
  }


  object Cow {
    def apply(name: String): Option[Cow] = Some(new Cow(name))
  }

  object CannibalCow {
    def apply(name: String): Option[CannibalCow] =
      if (name.startsWith("cannibal")) Some(new CannibalCow(name)) else None
  }

  Cow("Redhead").foreach(_.eat(new Grass))
  CannibalCow("cannibal Pyotr").foreach(_.eat(new Beef))
  CannibalCow("Cnnibal Sonya").foreach(_.eat(new Beef))
}
