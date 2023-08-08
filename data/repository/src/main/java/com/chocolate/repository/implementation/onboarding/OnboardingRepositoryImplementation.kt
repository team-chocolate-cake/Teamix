package com.chocolate.repository.implementation.onboarding

import com.chocolate.repository.service.OnboardingPreferencesDataSource
import repositories.onboarding.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImplementation @Inject constructor(
    private val onboardingPreferencesDataSource: OnboardingPreferencesDataSource
) : OnboardingRepository {
    override suspend fun shouldShowOnboarding(): Boolean =
        onboardingPreferencesDataSource.shouldShowOnboarding()

    override suspend fun setOnboardingShown() = onboardingPreferencesDataSource.setOnboardingShown()

}