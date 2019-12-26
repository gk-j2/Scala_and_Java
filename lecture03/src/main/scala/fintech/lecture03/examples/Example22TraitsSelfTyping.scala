package fintech.lecture03.examples

object Example22TraitsSelfTyping extends App {
  class Bird(val mass: Double)

  trait Flying { self: Bird =>
    def fly(): Unit
    def isHard = self.mass > 1.0
  }

  trait Swimming { self: Bird =>
    def swim(): Unit
    def isHard = self.mass > 10.0
  }

  class Penguin extends Bird(5.0) with Swimming {
    override def swim(): Unit = {
      println("Penguin swims" +
        (
          if (isHard)
            " with difficulty "
          else
            " without any problem"
        )
      )
    }
  }
  new Penguin().swim()
}
