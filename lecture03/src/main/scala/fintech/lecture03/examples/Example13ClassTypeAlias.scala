package fintech.lecture03.examples

import java.util.Date

object Example13ClassTypeAlias extends App {

  class LoggerSrc1 {
    def logs(from: Date, to: Date): Seq[(String, Date, List[(String, String)], String)] = ???
  }

  class LoggerSrc2 {
    type MyInt = Int
    type NamedParameter = (String, String)
    type LogRow = (String, Date, List[NamedParameter], String)
    def logs(from: Date, to: Date): Seq[LogRow] = ???
  }

  abstract class ALoggerSrc {
    type NamedParameter
    type DT
    type LogRow
    def logs(from: DT, to: DT): Seq[LogRow] = ???
  }

  class ConcreteLogger extends ALoggerSrc {
    type NamedParameter = (String, String)
    type DT = Date
    type LogRow = (String, Date, List[NamedParameter], String)
  }
}
