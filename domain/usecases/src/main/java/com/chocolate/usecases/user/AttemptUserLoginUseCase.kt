package com.chocolate.usecases.user

import repositories.UsersRepository
import javax.inject.Inject

class AttemptUserLoginUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        if(email.isNotEmpty() && password.isNotEmpty()){
            return usersRepository.userLogin(email,password)
        }
        return false
    }
}