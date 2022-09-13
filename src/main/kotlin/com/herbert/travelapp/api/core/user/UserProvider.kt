package com.herbert.travelapp.api.core.user

interface UserProvider {
    fun createUser(user: User) : User

    fun updateUser(user: User) : User

    fun searchUserByEmail(email: String) : User?

    fun searchByUserName(userName: String) : User?

    fun verifyEmail(email: String) : Boolean

    fun verifyUserName(userName: String) : Boolean

    fun deleteUser(userId: String) : Boolean
}