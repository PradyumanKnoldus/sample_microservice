package com.nashtechglobal
package bootstrap

import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax.EncoderOps
import model.PizzaOrder

// JsonSerializer: This object provides JSON encoding and decoding functionality for the PizzaOrder class using Circe library.
object JsonSerializer {
  // Implicit Circe Encoder and Decoder for PizzaOrder instances
  implicit val pizzaOrderEncoder: Encoder[PizzaOrder] = deriveEncoder[PizzaOrder]
  implicit val pizzaOrderDecoder: Decoder[PizzaOrder] = deriveDecoder[PizzaOrder]

  // Converts a PizzaOrder instance to its JSON representation as a string
  def toJson(pizzaOrder: PizzaOrder): String = pizzaOrder.asJson.noSpaces
}

