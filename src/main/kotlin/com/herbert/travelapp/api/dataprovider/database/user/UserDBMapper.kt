package com.herbert.travelapp.api.dataprovider.database.user

import com.herbert.travelapp.api.core.user.User
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserDBMapper {
    fun toUser(userDB: UserDB) : User

    fun toUserDB(user: User) : UserDB
}