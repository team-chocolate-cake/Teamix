package com.chocolate.usecases.onboarding

import kotlinx.coroutines.flow.Flow
import repositories.UsersRepository
import javax.inject.Inject

class ManageUserUsedAppUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        userRepository.setUserUsedAppForFirstTime(isComplete)
    }

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return userRepository.checkIfUserUsedAppOrNot()
    }
}