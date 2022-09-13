package com.herbert.travelapp.api.entrypoint.graphql.user

import com.herbert.graphql.model.SearchUserQueryResolver
import com.herbert.graphql.model.UserOutput
import com.herbert.graphql.model.VerifyEmailQueryResolver
import com.herbert.graphql.model.VerifyUserNameQueryResolver
import com.herbert.travelapp.api.core.user.UserProvider
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserQuery(
    val userProvider: UserProvider,
    val userMapper: UserMapper
) : VerifyEmailQueryResolver, VerifyUserNameQueryResolver, SearchUserQueryResolver {

    @QueryMapping
    override fun searchUser(@Argument email: String?, userName: String?): UserOutput? {
        val user = if(!email.isNullOrBlank()){
            userProvider.searchUserByEmail(email)
        }else if(!userName.isNullOrBlank()){
            userProvider.searchByUserName(userName)
        }else{
            null
        }
        return user?.let{
            userMapper.toUserOutput(it)
        }
    }

    @QueryMapping
    override fun verifyEmail(@Argument email: String): Boolean {
        return userProvider.verifyEmail(email)
    }

    @QueryMapping
    override fun verifyUserName(@Argument userName: String): Boolean {
        return userProvider.verifyUserName(userName)
    }


}