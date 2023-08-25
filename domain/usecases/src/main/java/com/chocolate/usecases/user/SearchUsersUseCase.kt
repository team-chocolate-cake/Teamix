package com.chocolate.usecases.user

import com.chocolate.entities.user.User
import repositories.UsersRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {

    private var users: List<User>? = null

    suspend operator fun invoke(query: String): List<User> {
        return users?.let { users ->
            users.filter { it.fullName.contains(query, ignoreCase = true) }
        } ?: run {
            users = userRepository.getAllUsers()
            users?.filter { it.fullName.contains(query, ignoreCase = true) } ?: emptyList()
        }
    }
}