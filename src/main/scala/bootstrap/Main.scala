package com.nashtechglobal
package bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import routes.PizzaOrderRoutes
import service.PizzaOrderService
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("PizzaOrderSystem")
  implicit val materializer: Materializer = Materializer.matFromSystem(system)

  // Create an instance of Pizza Order Services
  private val pizzaOrderServices = new PizzaOrderService

  // Create an instance of PizzaOrderRoutes
  private val pizzaOrderRoutes = new PizzaOrderRoutes(pizzaOrderServices)

  // Bind the routes to a specific host and port
  private val bindingFuture = Http().newServerAt("localhost", 8080).bind(pizzaOrderRoutes.routes)
  bindingFuture.foreach { binding =>
    println(s"Server online at http://${binding.localAddress.getHostString}:${binding.localAddress.getPort}/")
  }
}
