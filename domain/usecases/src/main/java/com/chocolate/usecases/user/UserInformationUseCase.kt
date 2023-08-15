package com.chocolate.usecases.user

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.user.Settings
import com.chocolate.entities.user.UserState
import kotlinx.coroutines.flow.Flow
import repositories.UsersRepository
import javax.inject.Inject

class UserInformationUseCase @Inject constructor(
    private val usersRepository: UsersRepository,

) {
    suspend operator fun invoke() = usersRepository.getOwnUser()

    suspend fun updateUserInformation(settings: Settings) {
        val oldUserInformation = invoke()
        settings.takeIf {newUserInformation->
            oldUserInformation.email != newUserInformation.email ||
            oldUserInformation.fullName != newUserInformation.fullName
        }?.run {
            usersRepository.updateSettings(settings)
        }
        throw TeamixException("It is not possible to change the same name or email")
    }

    suspend fun setUserLoginState(isComplete: Boolean){
        usersRepository.setUserLoginState(isComplete)
    }

    suspend fun attemptUserLogin(email: String, password: String): Boolean {
        if(email.isNotEmpty() && password.isNotEmpty()){
            return usersRepository.userLogin(email,password)
        }
        return false
    }

    suspend fun getUserLoginStatus(): Flow<Boolean> {
        return usersRepository.getUserLoginState()
    }

}