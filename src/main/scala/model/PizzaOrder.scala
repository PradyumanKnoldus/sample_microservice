package com.nashtechglobal
package model

case class PizzaOrder( orderId: Option[String],
                       pizzaType: String,
                       pizzaCategory: String,
                       size: String,
                       toppings: List[String],
                       crustType: String,
                       quantity: Int,
                       orderTime: Option[String],
                       additionalDetails: Option[String],
                       userId: String)

/*

{
  "orderId": "123456",
  "pizzaType": "Margherita",
  "pizzaCategory": "Vegetarian",
  "size": "Large",
  "toppings": ["Tomato", "Mozzarella", "Basil"],
  "crustType": "Thin Crust",
  "quantity": 2,
  "orderTime": "2024-01-31T12:30:00",
  "additionalDetails": "Extra crispy crust",
  "userId": "user123"
}

*/