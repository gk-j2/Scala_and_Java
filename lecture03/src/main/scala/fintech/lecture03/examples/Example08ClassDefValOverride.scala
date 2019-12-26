package fintech.lecture03.examples

object Example08ClassDefValOverride extends App {

  class ParentClass {
    def anotherMethod: String = {
      println("call on anotherMethod")

      "anotherMethod"
    }

    val someVal: String = ""

    println("Parent class instance initialized")
  }


  class ChildClass extends ParentClass {
    override val anotherMethod: String = "overridden " + super.anotherMethod
//    override def someVal: String = "" // error

    println("Child class instance initialized")
  }

  val parentClassInstance = new ParentClass
  println(s"parentClassInstance.anotherMethod == ${parentClassInstance.anotherMethod}")
  println(s"parentClassInstance.anotherMethod == ${parentClassInstance.anotherMethod}")

  println

  val childClassInstance = new ChildClass
  println(s"childClassInstance.anotherMethod == ${childClassInstance.anotherMethod}")
  println(s"childClassInstance.anotherMethod == ${childClassInstance.anotherMethod}")


}
