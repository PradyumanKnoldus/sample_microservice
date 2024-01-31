package com.nashtechglobal
package routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import model.PizzaOrder

import com.nashtechglobal.bootstrap.JsonSerializer
import com.nashtechglobal.db.kafka.KafkaProducer
import com.nashtechglobal.db.redis.RedisService


class PizzaOrderRoutes(implicit system: ActorSystem) {
  implicit val pizzaOrderFormat: RootJsonFormat[PizzaOrder] = jsonFormat10(PizzaOrder)
  implicit val executionContext = system.dispatcher

  val route: Route =
    path("api" / "order") {
      post {
        entity(as[PizzaOrder]) { pizzaOrder =>
          RedisService.writeToRedis(pizzaOrder.userId, JsonSerializer.toJson(pizzaOrder))
          KafkaProducer.sentToKafka(pizzaOrder)
          complete(StatusCodes.OK, s"Order ${pizzaOrder.orderId.getOrElse("N/A")} Successful.")
        }
      }
    }
}

