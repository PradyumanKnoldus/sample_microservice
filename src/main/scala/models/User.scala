package models

case class User(
                 userId: Int,
                 userName: String,
                 password: String,
                 firstName: String,
                 lastName: String,
                 gender: String,
                 dob: String,
                 email: String,
                 phoneNumber: Long,
                 address: String,
                 city: String,
                 state: String,
                 pinCode: Long,
                 country: String
               )