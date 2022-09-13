package com.herbert.travelapp.api.dataprovider.email

import com.herbert.travelapp.api.core.user.User

interface EmailProvider {
    fun sendEmailUserCreated(user: User) : Boolean

    fun sendEmailUserPasswordUpdated(user: User) : Boolean
}