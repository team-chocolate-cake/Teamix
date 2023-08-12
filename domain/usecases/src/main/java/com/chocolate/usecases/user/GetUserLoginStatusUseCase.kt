package com.chocolate.usecases.user

import repositories.UsersRepositories
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
) {
    suspend operator fun invoke(): Boolean{
        return usersRepositories.getUserLoginState()
    }
}