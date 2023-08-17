package com.chocolate.usecases.user

import repositories.UsersRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val usersRepositories: UsersRepository
) {
    suspend operator fun invoke(){
        usersRepositories.clearLoginInformation()
    }
}