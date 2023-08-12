package com.chocolate.usecases.onboarding

import kotlinx.coroutines.flow.Flow
import repositories.OnboardingRepository
import javax.inject.Inject

class GetOnboardingStateUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke(): Flow<Boolean> = onboardingRepository.getOnboardingState()

}