package com.chocolate.usecases.user

import repositories.UsersRepositories
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
) {

    suspend operator fun invoke(email: String, password: String): Boolean {
       if(email.isNotEmpty() && password.isNotEmpty()){
           return usersRepositories.userLogin(email,password)
       }
        return false
    }

}