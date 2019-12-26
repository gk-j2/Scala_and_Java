class F{}
class B(base : Int){
  def met(i: Int): Int = base+i
}
implicit def f2b0(foo: F):B=new B(0)
implicit def f2b1(foo: F):B=new B(1)

new F().met(5)