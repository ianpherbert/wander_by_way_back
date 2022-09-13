package com.herbert.travelapp.api.dataprovider.database.user

import com.herbert.travelapp.api.core.user.UserStatus

class UserDB {
    val userName: String? = null

    val email: String? = null

    val status: List<UserStatus>? = null

    val firstName: String? = null

    val lastName: String? = null

    val phoneNumber: String? = null

    val country: String? = null

    val region: String? = null

    val security: UserDBSecurity? = null
}

class UserDBSecurity{
    val securityId: String? = null
}

