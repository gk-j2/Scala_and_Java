package fintech.answers02

import fintech.exercises02.MyInt

trait Answers {

  def exp1 = (MyInt(1) :+ MyInt(2)) :+ MyInt(3)
    // (1 + 2) + 3
    // 6

  def exp2 = MyInt(1) :: (MyInt(2) :: MyInt(3))
    // (MyInt(3).::(MyInt(2))).::(MyInt(1))
    // MyInt(3 - 2 * 2).::(MyInt(1))
    // -1 - 2 * 1
    // -3

  def exp3 = (MyInt(1) :- MyInt(2)) += (MyInt(3) :: MyInt(2) :: MyInt(1))
    // (MyInt(1) :- MyInt(2)) += (MyInt(3) :: (MyInt(1).::(MyInt(2))))
    // (MyInt(1) :- MyInt(2)) += ((MyInt(1).::(MyInt(2))).::(MyInt(3)))
    // (MyInt(1) :- MyInt(2)) += (MyInt(1 - 2 * 2).::(MyInt(3)))
    // (MyInt(1) :- MyInt(2)) += MyInt(-3 - 2 * 3)
    // (MyInt(1) :- MyInt(2)) += MyInt(-9)
    // MyInt(1 - 2) += MyInt(-9)
    // MyInt(-1) += MyInt(-9)
    // MyInt(-1 + 2 * (-9))
    // -19

  def exp4 = ((MyInt(2) && MyInt(1)) || (MyInt(1) && MyInt(5))) += MyInt(2)
    // (MyInt(2) || MyInt(1)) += MyInt(2)
    // MyInt(1) += MyInt(2)
    // 1 + 2 * 2
    // 5
}
