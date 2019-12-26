package fintech.lecture03.examples

object Example10ClassFinal extends App {

  class ParentClass {
    final def anotherMethod: String = "final anotherMethod"
  }

  class ChildClass extends ParentClass {
//    override def anotherMethod: String = "overridden " + super.anotherMethod // error
  }


  final class FinalClass {
  }

//  class SomeClass extends FinalClass // error

  val childClassInstance = new ChildClass
  println(s"childClassInstance.anotherMethod == ${childClassInstance.anotherMethod}")
}
