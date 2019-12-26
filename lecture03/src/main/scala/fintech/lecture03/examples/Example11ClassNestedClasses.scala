package fintech.lecture03.examples

object Example11ClassNestedClasses extends App {

  class OuterClass {
    val a = 12

    class InnerClass {
      println(a)

      def anotherMethod: String = "anotherMethod"
    }
  }

  val outerInstance = new OuterClass
  val outerInstance2 = new OuterClass
  val innerInstance = new outerInstance.InnerClass
  val innerInstance2: outerInstance.InnerClass = new outerInstance.InnerClass
  val innerInstance3: OuterClass#InnerClass = innerInstance2

//  val incompatible: outerInstance.InnerClass = new outerInstance2.InnerClass // err
}
