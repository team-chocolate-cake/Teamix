package com.chocolate.repository.repository

import com.chocolate.repository.datastore.OnboardingDataStoreDataSource
import repositories.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataStoreDataSource: OnboardingDataStoreDataSource
) : OnboardingRepository {
    override suspend fun shouldShowOnboarding(): Boolean =
        onboardingDataStoreDataSource.shouldShowOnboarding()

    override suspend fun setOnboardingShown() = onboardingDataStoreDataSource.setOnboardingShown()

}