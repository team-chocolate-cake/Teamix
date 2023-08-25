package com.chocolate.usecases.user

import com.chocolate.entities.user.User
import repositories.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {

    suspend operator fun invoke(): List<User> {
        return repository.getAllUsers()
    }

    suspend fun searchUser(username: String): List<User>{
        return invoke().let {users->
            users.filter { it.fullName.contains(username,true) }
        }
    }

}