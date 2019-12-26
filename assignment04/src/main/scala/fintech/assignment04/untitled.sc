
val f: PartialFunction[Int, Unit] = {
  case x if x == 1 => println("One\n")
}

val v: PartialFunction[Int, Unit] = {
  case x if x == 2 => println("Two\n")
}

val g: PartialFunction[Int, Unit] = {
  case x if x == 3 => println("Three\n")
}

val x: PartialFunction[Int, Option[AnyVal]] = {
  case _ => None
}

val ans = v orElse g orElse f orElse x

ans(2)
x(_)