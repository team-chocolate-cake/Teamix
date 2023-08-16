package com.chocolate.usecases.user

import repositories.UsersRepository
import javax.inject.Inject

class SetUserLoginStateUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(isComplete: Boolean){
        usersRepository.setUserLoginState(isComplete)
    }
}