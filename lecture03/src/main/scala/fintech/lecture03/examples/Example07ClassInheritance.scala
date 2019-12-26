package fintech.lecture03.examples

object Example07ClassInheritance extends App {

  class ParentClass {
    def parentMethod: String = "parentMethod"

    def anotherMethod: String = "anotherMethod"
  }


  class ChildClass extends ParentClass {
    def childMethod: String = "childMethod"

    override def anotherMethod: String = "overridden " + super.anotherMethod
  }


  val childClassInstance = new ChildClass

  println(childClassInstance.parentMethod)
  println(childClassInstance.childMethod)
  println(childClassInstance.anotherMethod)
}
