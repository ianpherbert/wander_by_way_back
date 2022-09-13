package com.herbert.travelapp.api.core.user

class User (
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val country: String,
    val region: String? = null,
    val phoneNumber: String? = null
){
    lateinit var status: List<UserStatus>
    lateinit var security: UserSecurity
}

class UserSecurity{
    lateinit var temporaryPassword: String
    lateinit var securityId: String
}

enum class UserStatus{
    VERIFIED,
    PENDING,
    SUSPENDED,
    ACTIVE
}