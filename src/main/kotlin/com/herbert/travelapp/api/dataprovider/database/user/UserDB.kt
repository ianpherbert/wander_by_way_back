package com.herbert.travelapp.api.dataprovider.database.user

import com.herbert.travelapp.api.core.user.UserStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class UserDB {
    @Id
    var id: String? = null

    @Indexed(unique = true)
    var userName: String? = null

    @Indexed(unique = true)
    var email: String? = null

    var status: List<UserStatus>? = null

    var firstName: String? = null

    var lastName: String? = null

    var phoneNumber: String? = null

    var country: String? = null

    var region: String? = null

    var security: UserDBSecurity? = null
}

class UserDBSecurity{
    var securityId: String? = null
}

