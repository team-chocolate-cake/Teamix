package com.chocolate.usecases.user

import repositories.UsersRepositories
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
) {
    suspend operator fun invoke(){
        usersRepositories.clearLoginInformation()
    }
}