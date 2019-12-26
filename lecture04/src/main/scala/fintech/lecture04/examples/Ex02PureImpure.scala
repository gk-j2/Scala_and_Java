package fintech.lecture04.examples

object Ex02PureImpure {
  object impure_cafe {

    case class Coffee(price: Double = 0)

    trait CreditCard {
      // кредитка сама себя чаржит
      // работа с внешним миром внутри самой кредитки
      def charge(price: Double)
    }

    class Cafe {
      def buyCoffee(cc: CreditCard): Coffee = {
        val cup = new Coffee()
        cc.charge(cup.price)
        cup
      }
    }


  }

  object better_cafe {


    case class Coffee(price: Double = 0)

    trait CreditCard

    trait Payments {
      // уже лучше - есть некий payemnts, который умеет "чаржить кредитку"
      // работа с внешним миром его обязанность
      def charge(card: CreditCard, price: Double)
    }

    class BetterCafe {
      def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
        val cup = new Coffee()
        p.charge(cc, cup.price)
        cup
      }
    }


  }

  object pure_cafe {
    // результат чаржа - данные, которые могут быть обработыны потом
    // в том числе скомбинированы

    case class Coffee(price: Double = 0)
    trait CreditCard

    class PureCafe {
      def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
        val cup = new Coffee()
        (cup, Charge(cc, cup.price))
      }
    }

    case class Charge(cc: CreditCard, amount: Double) {
      def combine(other: Charge): Option[Charge] =
        if (cc == other.cc)
          Some(Charge(cc, amount + other.amount))
        else
          None
    }

  }
}