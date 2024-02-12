package db

import dao.DAO
import models.User

import scala.util.{Failure, Success, Try}
import java.sql.{ResultSet, Statement}
import scala.annotation.tailrec

class UserDB extends DAO {

  private val statement: Statement = Connection.connection().createStatement()

  def addUser(user: User): String = {
    getUserById(user.userId) match {
      case Some(value) => s"User ${value.userId} is Already exists!!!"
      case None =>
        val query =
          s"""
             |INSERT INTO
             |userInfo (userId, userName, password, firstName, lastName, gender, dob, email, phoneNumber, address, city, state, pinCode, country)
             |VALUES (${user.userId},'${user.userName}','${user.password}','${user.firstName}', '${user.lastName}', '${user.gender}', '${user.dob}', '${user.email}', ${user.phoneNumber}, '${user.address}', '${user.city}', '${user.state}', ${user.pinCode}, '${user.country}');
             |"""
            .stripMargin
        Try(statement.executeQuery(query))
        s"User ${user.userId} is Inserted Successfully!!!"
    }
  }

  def getUserById(userId: Int): Option[User] = {
    val query =
      s"""
         |SELECT *
         |FROM userInfo
         |WHERE userId=$userId;
         |"""
        .stripMargin
    queryExecution(query) match {
      case value: List[User] if value.length == 1 => Some(value.head)
      case _ => None
    }
  }

  def getAllUsers: List[User] = {
    val query =
      """
        |SELECT *
        |FROM userInfo
        |ORDER BY userId;
        |"""
        .stripMargin
    queryExecution(query)
  }

  private def queryExecution(query: String): List[User] = {
    Try(statement.executeQuery(query)) match {
      case Success(resultSet) => resultSetToList(resultSet, List())
      case Failure(_) => List()
    }
  }

  @tailrec
  private def resultSetToList(resultSet: ResultSet, userList: List[User]): List[User] = {
    if (resultSet.next()) {
      val userId = resultSet.getInt("userId")
      val userName = resultSet.getString("userName")
      val password = resultSet.getString("password").concat("*****")
      val firstName = resultSet.getString("firstName")
      val lastName = resultSet.getString("lastName")
      val gender = resultSet.getString("gender")
      val dob = resultSet.getString("dob")
      val email = resultSet.getString("email")
      val phoneNumber = resultSet.getLong("phoneNumber")
      val address = resultSet.getString("address")
      val city = resultSet.getString("city")
      val state = resultSet.getString("state")
      val pinCode = resultSet.getLong("pinCode")
      val country = resultSet.getString("country")

      val updatedUserList = userList ::: List(User(userId, userName, password, firstName, lastName, gender, dob, email, phoneNumber, address, city, state, pinCode, country))
      resultSetToList(resultSet, updatedUserList)
    } else userList
  }

  def updateUserNameById(userId: Int, valueToUpdate: String): String = {
    getUserById(userId) match {
      case Some(value) =>
        val query =
          s"""
             |UPDATE userInfo
             |SET userName = '$valueToUpdate'
             |WHERE userId = ${value.userId};
             |"""
            .stripMargin
        Try(statement.executeQuery(query))
        s"User $userId is Updated Successfully!!!"
      case None => s"User Doesn't exists."
    }
  }

  def updateUserFields(userId: Int, fieldsToUpdate: User): String = {
    getUserById(userId) match {
      case Some(value) =>
        val query =
          s"""
             |UPDATE userInfo
             |SET
             |userName = '${fieldsToUpdate.userName}',
             |password = '${fieldsToUpdate.password}',
             |firstName = '${fieldsToUpdate.firstName}',
             |lastName = '${fieldsToUpdate.lastName}',
             |email = '${fieldsToUpdate.email}',
             |phoneNumber = ${fieldsToUpdate.phoneNumber},
             |address = '${fieldsToUpdate.address}',
             |city = '${fieldsToUpdate.city}',
             |state = '${fieldsToUpdate.state}',
             |pinCode = ${fieldsToUpdate.pinCode},
             |country = '${fieldsToUpdate.country}'
             |WHERE userId = ${value.userId};
             |"""
            .stripMargin
        Try(statement.executeQuery(query))
        s"User $userId is Updated Successfully!!!"
      case None => s"User Doesn't exists."
    }
  }

  def deleteUserById(userId: Int): String = {
    getUserById(userId) match {
      case Some(value) =>
        val query =
          s"""
             |DELETE
             |FROM userInfo
             |WHERE userId = ${value.userId};
             |"""
            .stripMargin
        Try(statement.executeQuery(query))
        s"User $userId is Deleted Successfully!!!"
      case None => s"User Doesn't exists."
    }
  }

  def deleteAllUsers(): String = {
    val query =
      s"""
         |DELETE
         |FROM userInfo;
         |"""
        .stripMargin
    Try(statement.executeQuery(query))
    s"All Row's Deleted Successfully!!!"
  }
}