package com.chocolate.usecases.onboarding

import repositories.OnboardingRepository
import javax.inject.Inject

class SetOnboardingScreenShownUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke() {
        onboardingRepository.setOnboardingShown()
    }

}