package com.nashtechglobal
package model

case class CustomerInfo( userId: String,
                         firstName: String,
                         lastName: Option[String],
                         address: String,
                         phoneNumber: String,
                         email: String)
