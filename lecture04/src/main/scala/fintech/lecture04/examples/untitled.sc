

val fab = (a: Int) => a % 2 == 0

val f1 = (a: Int) => a / 3
val f2 = (a: Int) => a + 1
val f3 = f1.compose(f2)
f3(7)