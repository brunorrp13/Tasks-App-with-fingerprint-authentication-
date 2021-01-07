package business

import entity.UserEntity
import repository.RegisterRepository

class LoginBusiness {
    private val userList = RegisterRepository.getUsers()

    fun validation (user: UserEntity): Boolean {
        if (user.login == "") {
            throw Exception("Login is required!")
        }

        if (user.password == "") {
            throw Exception("Password is required!")
        }

        if (userList.size == 0) {
            throw Exception("User not found!")
        } else {

            for (item in userList.withIndex()) {
                if (item.value.login == user.login && item.value.password == user.password) {
                    return true
                    break
                } else if (item.value.login == user.login && item.value.password != user.password) {
                    throw Exception("Wrong password!")
                }
            }
            throw Exception("User not found!")
            return false

        }
    }
}