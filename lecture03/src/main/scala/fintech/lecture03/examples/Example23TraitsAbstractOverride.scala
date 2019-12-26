package fintech.lecture03.examples

object Example23TraitsAbstractOverride extends App {

  trait Calculator {
    val zero = 0
    def calculate(i: Int): Int
  }

  trait AndThenDouble extends Calculator {
    abstract override def calculate(i: Int): Int = {
      println("double")
      super.calculate(i) * 2
    }
  }

  trait AndThenIncreaseByOne extends Calculator {
    abstract override def calculate(i: Int): Int = {
      println("increment")
      super.calculate(i) + 1
    }
  }

  trait AndThenSquare extends Calculator {
    abstract override def calculate(i: Int): Int = {
      println("square")
      val calculated = super.calculate(i)
      calculated * calculated
    }
  }

  class Dummy extends Calculator {
    override def calculate(i: Int): Int = {
      println("calculate")
      i
    }
  }

  object Funky extends Dummy with AndThenIncreaseByOne with AndThenSquare with AndThenDouble

//  println(Funky.calculate(1))
  println(Funky.calculate(2))
}
