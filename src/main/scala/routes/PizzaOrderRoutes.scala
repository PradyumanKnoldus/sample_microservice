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
import service.PizzaOrderService

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

class PizzaOrderRoutes(orderService: PizzaOrderService)(implicit system: ActorSystem) {
  // Implicit JSON format for PizzaOrder to facilitate JSON serialization/deserialization.
  implicit val pizzaOrderFormat: RootJsonFormat[PizzaOrder] = jsonFormat10(PizzaOrder)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  // Define API route for handling pizza orders.
  val routes: Route = pathPrefix("pizzaOrder") {
    pathEndOrSingleSlash {
      postOrder
    }
  }

  // Method to Post Pizza Order
  private def postOrder: Route = post {
    entity(as[PizzaOrder]) { pizzaOrder =>
      onComplete(orderService.orderPizza(pizzaOrder.pizzaType, pizzaOrder.pizzaCategory, pizzaOrder.size, pizzaOrder.toppings, pizzaOrder.crustType, pizzaOrder.quantity, pizzaOrder.additionalDetails, pizzaOrder.userId)) {
        case Success(Right(placedOrder)) =>
          complete(StatusCodes.OK, placedOrder)
        case Success(Left(message)) =>
          complete(StatusCodes.Conflict, message)
        case Failure(exception) =>
          complete(StatusCodes.InternalServerError, s"An error occurred: ${exception.getMessage}")
      }
    }
  }
}

