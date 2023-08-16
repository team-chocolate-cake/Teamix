package com.chocolate.usecases.onboarding

import repositories.OnboardingRepository
import javax.inject.Inject

class SetOnboardingStateUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(isComplete: Boolean) =
        onboardingRepository.setOnboardingState(isComplete)
}