package com.chocolate.usecases.usecase.onboarding

import kotlinx.coroutines.flow.Flow
import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class ManageUserUsedAppUseCase @Inject constructor(
    private val userRepository: MemberRepository
) {
    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        userRepository.setUserUsedAppForFirstTime(isComplete)
    }

    fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return userRepository.checkIfUserUsedAppOrNot()
    }
}