package org.urfu.scalafp.lection04.ex1

import org.urfu.scalafp.lection04.ex1.alias.OneOfT

sealed trait Union
trait :|:[+H, +T <: Union] extends Union
sealed trait UNil extends Union

sealed trait MemberOf[T, U <: Union]
object MemberOf {
  // If H is the first type in an Union U, we trivially know that H is in U
  implicit def baseCase[H, U <: Union]: MemberOf[H, H :|: U] = null
  // If T is in the union U, we know T is in the Union H:|:U for any H
  implicit def recursiveCase[H, U <: Union, T](
      implicit member: T MemberOf U): T MemberOf (H :|: U) = null
}

trait SubUnionOf[U <: Union, T <: Union]
object SubUnionOf {
  // If U1 is a member of U, then U1 :|: UNil is most definitely a subunion of U
  implicit def fromMemberCase[U1, U <: Union](
      implicit s: U1 MemberOf U): SubUnionOf[U1 :|: UNil, U] = null

  // If T1 is a member of U, and T is a subunion of U, then T1 :|: T is a subunion of U
  implicit def recursiveCase[U <: Union, T1, T <: Union](
      implicit mem: T1 MemberOf U,
      sub: T SubUnionOf U
  ): (T1 :|: T) SubUnionOf U = null
}

trait OneOf[T, U <: Union]
object OneOf {
  // If T is a member of the union U then we know T is ‘one of’ U, by our definition of what ‘OneOf’ means
  implicit def fromMemberProof[U <: Union, T](
      implicit s: T MemberOf U): T OneOf U = null

  // If we have evidence OneOf[T, Ev] it means T is a member of Ev, and if we know Ev
  // is a subunion of Target, then we can deduce T is also one of Target
  implicit def nestedOneOfProof[T, Ev <: Union, Target <: Union](
      implicit ev: T OneOf Ev,
      sub: Ev SubUnionOf Target
  ): T OneOf Target = null
}

object alias {
  type OneOfT[H <: Union] = {
    type l[T] = OneOf[T, H]
  }
}

object Testing {
  def foo[T: OneOfT[Int :|: String :|: Boolean :|: UNil]#l](t: T): String = t match {
    case s: String => s
    case b: Boolean => b.toString
    case i: Int => i.toString
  }

  foo(5)
}
