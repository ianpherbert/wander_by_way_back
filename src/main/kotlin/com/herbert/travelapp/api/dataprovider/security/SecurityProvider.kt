package com.herbert.travelapp.api.dataprovider.security

import com.herbert.travelapp.api.core.user.User
import com.herbert.travelapp.api.core.user.UserSecurity

interface SecurityProvider {
    fun createUser(user: User) : UserSecurity

    fun updateUserPassword(user: User) : UserSecurity
}