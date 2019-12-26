package fintech.exercises05


/**
 * Напишите тип Maybe[+T].
 */
sealed trait Maybe2[+T]

/**
 * Напишите метод getOrElse, который возвращает значение из Maybe, или переданное значение по умолчанию
 * Напишите метод orElse, который возвращает текущий Maybe если он содержит значение, или переданный второй Maybe
 *
 * val maybeString: Maybe2[String] = Just2("string")
 * val missingString: Maybe2[String] = Missing2[String]
 *
 * val orAnother = maybeString.getOrElse("anotherString")
 * println(orAnother)
 *
 * val orInt = missingString.getOrElse(1) // val orInt: Any
 * println(orInt)
 */
