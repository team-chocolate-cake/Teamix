package com.chocolate.usecases.onboarding

import kotlinx.coroutines.flow.Flow
import repositories.MemberRepository
import javax.inject.Inject

class ManageUserUsedAppUseCase @Inject constructor(
    private val userRepository: MemberRepository
) {
    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        userRepository.setUserUsedAppForFirstTime(isComplete)
    }

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return userRepository.checkIfUserUsedAppOrNot()
    }
}