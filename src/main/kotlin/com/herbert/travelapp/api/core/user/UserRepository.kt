package com.herbert.travelapp.api.core.user

interface UserRepository {
    fun findById(id: String) : User?

    fun findByEmail(email: String) : User?

    fun findByUserName(userName: String) : User?

    fun verifyUserName(userName: String) : Boolean

    fun verifyEmail(email: String) : Boolean

    fun saveUser(user: User) : User

    fun deleteUser(user: User) : Boolean
}