package fintech.assignment04

import scala.{None, Some}
import scala.annotation.tailrec

object PartialCombo {
  def combo[I,T](funcs: List[PartialFunction[I,T]]): I => Option[T] = {

    def combineFunc(funcs: List[PartialFunction[I,T]], acc: PartialFunction[I,T]): PartialFunction[I,T] = {
      //case x if funcs.nonEmpty => combineFunc(funcs.tail, acc orElse funcs.head)
      case x if funcs.isEmpty => val res = acc

      /*if (funcs.isEmpty) acc
      else combineFunc*/
    }

    def noneFun: PartialFunction[I, Option[T]] = {
    case _ => None
  }
    val ans =
    if (funcs.isEmpty) noneFun(_)
    else if (funcs.length == 1) funcs.head.lift(_)
    else combineFunc(funcs.tail, funcs.head).lift(_)
  }
}