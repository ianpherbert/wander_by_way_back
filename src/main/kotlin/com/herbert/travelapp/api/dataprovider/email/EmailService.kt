package com.herbert.travelapp.api.dataprovider.email

import com.herbert.travelapp.api.core.user.User
import org.springframework.stereotype.Component

@Component
class EmailService : EmailProvider{
    override fun sendEmailUserCreated(user: User): Boolean {
        return true
        TODO("Not yet implemented")
    }

    override fun sendEmailUserPasswordUpdated(user: User): Boolean {
        return true
        TODO("Not yet implemented")
    }
}