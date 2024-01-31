package com.nashtechglobal
package bootstrap

import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax.EncoderOps
import model.PizzaOrder

object JsonSerializer {
  implicit val pizzaOrderEncoder: Encoder[PizzaOrder] = deriveEncoder[PizzaOrder]
  implicit val pizzaOrderDecoder: Decoder[PizzaOrder] = deriveDecoder[PizzaOrder]

  def toJson(pizzaOrder: PizzaOrder): String = pizzaOrder.asJson.noSpaces
}

