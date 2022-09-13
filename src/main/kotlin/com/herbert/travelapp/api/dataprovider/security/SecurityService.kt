package com.herbert.travelapp.api.dataprovider.security

import com.herbert.travelapp.api.core.user.User
import com.herbert.travelapp.api.core.user.UserSecurity
import org.springframework.stereotype.Component

@Component
class SecurityService : SecurityProvider {
    override fun createUser(user: User): UserSecurity {
        TODO("Create user in secu")
    }

    override fun updateUserPassword(user: User): UserSecurity {
        TODO("update user in secu")
    }
}