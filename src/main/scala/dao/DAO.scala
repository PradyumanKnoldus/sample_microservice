package dao

import models.User

trait DAO {

  def addUser(user: User): String

  def getUserById(userId: Int): Option[User]

  def getAllUsers: List[User]

  def updateUserNameById(userId: Int, valueToUpdate: String): String

  def updateUserFields(userId: Int, fieldsToUpdate: User): String

  def deleteUserById(userID: Int): String

  def deleteAllUsers(): String

}