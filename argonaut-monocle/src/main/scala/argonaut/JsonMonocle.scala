package argonaut

import monocle.{Prism, Traversal, Lens}
import monocle.function._
import Json._
import scalaz.{Each => _, Index => _, _}, Scalaz._

object JsonMonocle extends JsonMonocles

trait JsonMonocles {
  /** A Prism for JSON boolean values. */
  val jBoolPrism: Prism[Json, JsonBoolean] =
    Prism[Json, JsonBoolean](
      _.fold(None,
        b => Some(b),
        _ => None,
        _ => None,
        _ => None,
        _ => None)
    )(jBool)

  /** A Prism for JSON number values. */
  val jNumberPrism: Prism[Json, JsonNumber] =
    Prism[Json, JsonNumber](
      _.fold(None,
        _ => None,
        n => Some(n),
        _ => None,
        _ => None,
        _ => None)
    )(jNumber)

  /** A Prism for JSON number values. */
  val jBigDecimalPrism: Prism[Json, BigDecimal] =
    jNumberPrism composeIso JsonNumberMonocle.jNumberToBigDecimal

  /**
   * A Prism for JSON number values.
   * Note: It is an invalid Prism for NaN, +Infinity and -Infinity as they are not valid json.
   */
  val jDoublePrism: Prism[Json, Double] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToDouble

  val jFloatPrism: Prism[Json, Float] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToFloat

  /** A Prism for JSON BigInt values. */
  val jBigIntPrism: Prism[Json, BigInt] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToBigInt

  /** A Prism for JSON Long values. */
  val jLongPrism: Prism[Json, Long] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToLong

  /**  A Prism for JSON Int values. */
  val jIntPrism: Prism[Json, Int] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToInt

  /** A Prism for JSON Short values. */
  val jShortPrism: Prism[Json, Short] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToShort

  /** A Prism for JSON Byte values. */
  def jBytePrism: Prism[Json, Byte] =
    jNumberPrism composePrism JsonNumberMonocle.jNumberToByte

  /** A Prism for JSON string values. */
  val jStringPrism: Prism[Json, JsonString] =
    Prism[Json, JsonString](
      _.fold(None,
        _ => None,
        _ => None,
        s => Some(s),
        _ => None,
        _ => None)
    )(jString)

  /** A Prism for JSON array values. */
  val jArrayPrism: Prism[Json, JsonArray] =
    Prism[Json, JsonArray](
      _.fold(None,
        _ => None,
        _ => None,
        _ => None,
        a => Some(a),
        _ => None)
    )(jArray)

  /** A Prism for JSON object values. */
  val jObjectPrism: Prism[Json, JsonObject] =
    Prism[Json, JsonObject](
      _.fold(None,
        _ => None,
        _ => None,
        _ => None,
        _ => None,
        o => Some(o))
    )(jObject)


}
