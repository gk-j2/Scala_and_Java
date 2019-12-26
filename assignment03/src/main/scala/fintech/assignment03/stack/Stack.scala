package fintech.assignment03.stack

import scala.collection.mutable.ListBuffer

abstract class Stack {
  type T
  var stack: ListBuffer[T]

  def put(x: T): Unit = {
    stack.append(x)
  }

  def take: Option[T] = {
    Some(stack.remove(0))
  }
}

trait DoublingInt extends Stack {
  def doubling(): Unit = {
    for (i <- stack.indices) {
      stack.update(i, stack(i))
    }
  }
}

trait OnlyEvenInt extends Stack {
  def deleteOdd(): Unit = {
    for (i <- stack.indices) {
      if (i % 2 == 1)  stack.remove(i)
    }
  }
}

trait Log {
  type T
  def log(x: T): Unit
}

trait Logging extends Stack with Log {
  Logging.super.log(Logging.super.stack(0))
}
