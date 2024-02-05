package com.nashtechglobal
package model

// Case class representing customer information necessary fields.
case class CustomerInfo( userId: String,
                         firstName: String,
                         lastName: Option[String],
                         address: String,
                         phoneNumber: String,
                         email: String)
