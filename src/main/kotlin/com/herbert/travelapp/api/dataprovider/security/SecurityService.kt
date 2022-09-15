package com.herbert.travelapp.api.dataprovider.security

import com.herbert.travelapp.api.core.user.User
import com.herbert.travelapp.api.core.user.UserSecurity
import com.herbert.travelapp.api.core.user.UserStatus
import org.springframework.stereotype.Component

@Component
class SecurityService : SecurityProvider {
    override fun createUser(user: User): UserSecurity {
        return UserSecurity().apply {
            this.securityId = "securityId"
            this.temporaryPassword = "12345678910"
        }
        TODO("Create user in secu")
    }

    override fun updateUserPassword(user: User): UserSecurity {
        return UserSecurity().apply {
            this.securityId = "securityId"
            this.temporaryPassword = "12345678910"
        }
        TODO("update user in secu")
    }
}