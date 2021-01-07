package business

import entity.ContactEntity
import entity.UserEntity
import repository.ContactRepository
import repository.RegisterRepository

class CreateBusiness {
    private val userList = RegisterRepository.getUsers()

    fun createUser (user: UserEntity) {
        validation(user)
        RegisterRepository.saveRegister(user)
    }

    fun validation (user: UserEntity) {
        if (user.login == "") {
            throw Exception("Type a Login!")
        }

        if (user.password == "") {
            throw Exception("Type a password!")
        }

        for (item in userList.withIndex()) {
            if (item.value.login == user.login) {
                throw Exception("User already in the system!")
            }
        }

        }
    }

