package fintech.lecture03.examples

object Example06ClassPrivateThis extends App {

  class MyClass {
    private def privateField: String = ???
    private[this] def visibleOnlyForThis: String = ???

    def tryToCallOnAnotherObject(another: MyClass) = {
      this.privateField
      another.privateField

      this.visibleOnlyForThis
//      another.visibleOnlyForThis
    }

  }

  class ParametrizedMyClass[+T] {
//    private def classPrivateMethod(t: T): String = ???
    private[this] def visibleOnlyForThis(t: T): String = ???
  }


}
