package com.chocolate.usecases.user

import repositories.UsersRepositories
import javax.inject.Inject

class SetUserLoginStateUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
) {
    suspend operator fun invoke(isComplete: Boolean){
        usersRepositories.setUserLoginState(isComplete)
    }
}