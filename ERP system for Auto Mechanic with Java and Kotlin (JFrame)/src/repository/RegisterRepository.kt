package repository

import entity.ContactEntity
import entity.UserEntity

class RegisterRepository {

    companion object {
        private val userList = mutableListOf<UserEntity>()


        fun saveRegister(user: UserEntity) {
            validateRegister(user);
            userList.add(user);
        }

        fun getUsers(): List<UserEntity> {
            return userList
        }


        fun validateRegister(user: UserEntity) {

            for (item in userList.withIndex()) {
                if (item.value.login == user.login && item.value.password == user.password) {
                    throw Exception("User already registered!")
                    break
                }

            }
        }



    }

}