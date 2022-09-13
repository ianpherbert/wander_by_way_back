package com.herbert.travelapp.api.core.user

import com.herbert.travelapp.api.dataprovider.email.EmailProvider
import com.herbert.travelapp.api.dataprovider.security.SecurityProvider
import org.springframework.stereotype.Component

@Component
class UserService(
    val userRepository: UserRepository,
    val securityProvider: SecurityProvider,
    val emailProvider: EmailProvider
) : UserProvider {
    override fun createUser(user: User): User {
        userRepository.saveUser(user.apply {
            this.security = securityProvider.createUser(user)
        }).let {
            emailProvider.sendEmailUserCreated(user)
            return it
        }
    }

    override fun updateUser(user: User): User {
        userRepository.saveUser(user.apply {
            this.security = securityProvider.updateUserPassword(user)
        }).let {
            emailProvider.sendEmailUserPasswordUpdated(user)
            return it
        }
    }

    override fun searchUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun searchByUserName(userName: String): User? {
        return userRepository.findByUserName(userName)
    }

    override fun verifyEmail(email: String): Boolean {
        return userRepository.verifyEmail(email)
    }

    override fun verifyUserName(userName: String): Boolean {
        return userRepository.verifyUserName(userName)
    }
}