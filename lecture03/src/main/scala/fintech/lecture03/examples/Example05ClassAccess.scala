package fintech.lecture03.examples

import fintech.lecture03.examples.scopeA._

object Example05ClassAccess extends App {
  new Pblc

//  new Prtctd // invisible
//  new Prvt // invisible

//  new PrtctdScopeA // invisible
//  new PrvtScopeA // invisible

  new PrtctdExampes
  new PrvtExamples
}



package scopeA {

  class MyClass {
    def publicMethod: String = ???

    protected def protectedMethod: String = ???

    private def privateMethod: String = ???

    private[MyClass] def expClassPrivate: String = ???

    protected[MyClass] def expClassProtected: String = ???

    private[examples] def upToPackagePrivate: String = ???

    protected[examples] def upToPackageProtected: String = ???
  }

  class Pblc

  protected class Prtctd
  private class Prvt

  protected[scopeA] class PrtctdScopeA
  private[scopeA] class PrvtScopeA

  protected[examples] class PrtctdExampes
  private[examples] class PrvtExamples

  object inst {
    new Pblc

    new Prtctd
    new Prvt

    new PrtctdScopeA
    new PrvtScopeA

    new PrtctdExampes
    new PrvtExamples
  }

  package subScopeA {
    object inst {
      new Pblc

      new Prtctd
      new Prvt

      new PrtctdScopeA
      new PrvtScopeA

      new PrtctdExampes
      new PrvtExamples
    }
  }
}

package scopeB {

  import fintech.lecture03.examples.scopeA.MyClass

  class MyClassCont(clazz: MyClass) {
    clazz.publicMethod
//    clazz.protectedMethod // invisible
//    clazz.privateMethod // invisible
//    clazz.expClassProtected // invisible
//    clazz.expClassPrivate // invisible
    clazz.upToPackagePrivate
    clazz.upToPackageProtected
  }

  class MyClassInh extends MyClass {
    publicMethod
    protectedMethod
//    privateMethod // invisible
    expClassProtected
//    expClassPrivate // invisible
    upToPackagePrivate
    upToPackageProtected
  }

  object inst {
    new Pblc

//    new Prtctd // invisible
//    new Prvt // invisible

//    new PrtctdScopeA // invisible
//    new PrvtScopeA // invisible

    new PrtctdExampes
    new PrvtExamples
  }

}
