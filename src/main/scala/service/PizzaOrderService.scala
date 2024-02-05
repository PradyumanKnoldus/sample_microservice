package com.nashtechglobal
package service

import model.PizzaOrder
import bootstrap.JsonSerializer
import db.kafka.KafkaService
import db.redis.RedisService
import java.util.UUID
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

// Service class for handling pizza order-related operations.
class PizzaOrderService {
  // Method to Place Order
  def orderPizza(pizzaType: String, pizzaCategory: String, size: String, toppings: List[String], crustType: String, quantity: Int, additionalDetails: Option[String], userID: String): Future[Either[String, PizzaOrder]] = Future {
    val orderID = UUID.randomUUID().toString

    val orderTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formattedOrderTime = orderTime.format(formatter)

    val newOrder = PizzaOrder(orderId = Some(orderID), pizzaType = pizzaType, pizzaCategory = pizzaCategory, size = size, toppings = toppings, crustType = crustType, quantity = quantity, orderTime = Some(formattedOrderTime), additionalDetails = additionalDetails, userId = userID)

    RedisService.writeToRedis(newOrder.userId, JsonSerializer.toJson(newOrder))
    KafkaService.sentToKafka(newOrder)

    Right(newOrder)
  }.recoverWith {
    case exception: Exception => Future.successful(Left(s"Unexpected Exception Occurred! Unable to place Order! ${exception.getMessage}"))
  }
}
