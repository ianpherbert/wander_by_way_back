package com.herbert.travelapp.api.entrypoint.graphql.user

import com.herbert.graphql.model.CreateUserMutationResolver
import com.herbert.graphql.model.DeleteUserMutationResolver
import com.herbert.graphql.model.UpdateUserMutationResolver
import com.herbert.graphql.model.UserInput
import com.herbert.graphql.model.UserOutput
import com.herbert.travelapp.api.core.user.UserProvider
import graphql.GraphQLException
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class UserMutation(
    val userProvider: UserProvider,
    val userMapper: UserMapper
) : CreateUserMutationResolver, UpdateUserMutationResolver, DeleteUserMutationResolver {
    @MutationMapping
    override fun createUser(@Argument userInput: UserInput): UserOutput {
        return try{
            userMapper.toUser(userInput).let {
                userProvider.createUser(it)
            }.let {
                userMapper.toUserOutput(it)
            }
        }catch (ex: Exception){
            throw GraphQLException("Could not create user")
        }
    }

    @MutationMapping
    override fun updateUser(@Argument userInput: UserInput): UserOutput {
        return userMapper.toUser(userInput).let {
            userProvider.updateUser(it)
        }.let {
            userMapper.toUserOutput(it)
        }
    }

    @MutationMapping
    override fun deleteUser(userId: String): Boolean {
        return userProvider.deleteUser(userId)
    }

}