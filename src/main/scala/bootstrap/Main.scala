package com.nashtechglobal
package bootstrap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import routes.PizzaOrderRoutes

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("PizzaOrderSystem")
  implicit val materializer: Materializer = Materializer.matFromSystem(system)

  // Create an instance of PizzaOrderRoutes
  val pizzaOrderRoutes = new PizzaOrderRoutes

  // Bind the routes to a specific host and port
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(pizzaOrderRoutes.route)

  println(s"Server online at http://localhost:8080/")
}
