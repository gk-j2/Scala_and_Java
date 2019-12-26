package fintech.lecture03.examples

import java.lang._
import scala._
import Predef._

import scala.collection.mutable.{Set => MSet, Map => _, _}
import scala.collection.mutable

object Example25ImportSyntax extends App {
  import Example23TraitsAbstractOverride.{Calculator, Funky}

  val map = mutable.Map(1 -> 2)

  println(s"Funky.calculate(12) == ${Funky.calculate(12)}")

  def doSomething(c: Calculator): Unit = {
    import c._

    println(zero)
    println(s"calculate(12) == ${calculate(12)}")
  }

  val a: Int = 12

  import a./

  println(s"/(2) == ${/(2)}")
}
