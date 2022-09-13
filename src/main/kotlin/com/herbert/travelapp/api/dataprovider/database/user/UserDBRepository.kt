package com.herbert.travelapp.api.dataprovider.database.user

import com.herbert.travelapp.api.core.user.User
import com.herbert.travelapp.api.core.user.UserRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
interface UserDBRepository : MongoRepository<UserDB, String>{
    fun findByEmail(email: String) : UserDB?

    fun findByUserName(userName: String) : UserDB?

    fun existsByEmail(email: String) : Boolean

    fun existsByUserName(userName: String) : Boolean
}
@Component
class UserDBService(
    val userDBRepository: UserDBRepository,
    val userDBMapper: UserDBMapper
) : UserRepository{
    override fun findById(id: String): User? {
        return userDBRepository.findById(id).let {
            if(it.isEmpty) return null
            userDBMapper.toUser(it.get())
        }
    }

    override fun findByEmail(email: String): User? {
        return userDBRepository.findByEmail(email)?.let {
            userDBMapper.toUser(it)
        }
    }

    override fun findByUserName(userName: String): User? {
        return userDBRepository.findByUserName(userName)?.let{
            userDBMapper.toUser(it)
        }
    }

    override fun verifyUserName(userName: String): Boolean {
        return userDBRepository.existsByUserName(userName)
    }

    override fun verifyEmail(email: String): Boolean {
        return userDBRepository.existsByEmail(email)
    }

    override fun saveUser(user: User): User {
        return userDBMapper.toUserDB(user).let {
            userDBRepository.save(it)
        }.let{
            userDBMapper.toUser(it)
        }
    }

    override fun deleteUser(user: User): Boolean {
        return userDBMapper.toUserDB(user).let {
            try {
                userDBRepository.delete(it)
                true
            }catch (ex: Exception){
                println("Could not delete user:  ${ex.message}")
                false
            }
        }
    }

}