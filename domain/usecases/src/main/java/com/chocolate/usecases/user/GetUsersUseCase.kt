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

}