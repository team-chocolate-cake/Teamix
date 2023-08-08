package com.chocolate.usecases.onboarding

import repositories.onboarding.OnboardingRepository
import javax.inject.Inject

class ShouldShowOnboardingScreenUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(): Boolean = onboardingRepository.shouldShowOnboarding()

}