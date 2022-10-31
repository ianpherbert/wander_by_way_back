package com.herbert.travelapp.api.entrypoint.graphql.user

import com.herbert.graphql.model.UserInput
import com.herbert.graphql.model.UserOutput
import com.herbert.travelapp.api.core.user.User
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    fun toUser(userInput: UserInput) : User

    fun toUserOutput(user: User) : UserOutput
}