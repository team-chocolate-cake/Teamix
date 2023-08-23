package com.chocolate.usecases.onboarding

import repositories.UsersRepository
import javax.inject.Inject

class ManageUserUsedAppUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        userRepository.setUserUsedAppForFirstTime(isComplete)
    }

    suspend fun checkIfUserUsedAppOrNot(): Boolean {
        return userRepository.checkIfUserUsedAppOrNot()
    }
}