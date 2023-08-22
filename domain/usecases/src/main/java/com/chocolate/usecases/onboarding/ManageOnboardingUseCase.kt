package com.chocolate.usecases.onboarding

import repositories.OnboardingRepository
import javax.inject.Inject

class ManageOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend fun setOnboardingState(isComplete: Boolean) {
        onboardingRepository.setOnboardingState(isComplete)
    }

    suspend fun getOnboardingState(): Boolean {
        return onboardingRepository.getOnboardingState()
    }
}