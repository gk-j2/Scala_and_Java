package fintech.lecture05

object Slide07Example1 {
  sealed trait Animal {
    def name: String
  }

  trait Pet extends Animal
  class Lion extends Animal {
    override def name = "Lion"
  }

  class Dog extends Pet {
    override def name = "Dog"
  }
  class Cat extends Pet {
    override def name = "Pet"
  }
}

object Slide07Example2 {
  import Slide07Example1._

  class PetContainer[P <: Pet](p: P) {

    def getPetName: String = p.name
  }

  val dogContainer = new PetContainer(new Dog)

  // val lionContainer = new PetContainer(new Lion)
}
