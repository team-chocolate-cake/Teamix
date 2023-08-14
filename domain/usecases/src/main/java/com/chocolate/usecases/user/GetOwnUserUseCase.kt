package com.chocolate.usecases.user

import com.chocolate.entities.user.OwnerUser
import repositories.UsersRepositories
import javax.inject.Inject

class GetOwnUserUseCase @Inject constructor(private val usersRepositories: UsersRepositories) {
    suspend operator fun invoke(): OwnerUser  {
     return usersRepositories.getOwnUser()
    }
}