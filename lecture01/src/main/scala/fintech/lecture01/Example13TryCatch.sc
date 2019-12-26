def weirdDivide(a: Int, b: Int) =
  try {
    a / b
  } catch {
    case ae: ArithmeticException => 0
    case e: Exception => -1
  } finally {
    println("BAM!!!")
  }

weirdDivide(4, 2)
weirdDivide(4, 0)