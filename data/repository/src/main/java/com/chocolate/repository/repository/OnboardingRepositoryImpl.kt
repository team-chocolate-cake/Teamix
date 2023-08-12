package com.chocolate.repository.repository

import com.chocolate.repository.datastore.OnboardingDataStoreDataSource
import kotlinx.coroutines.flow.Flow
import repositories.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataStoreDataSource: OnboardingDataStoreDataSource
) : OnboardingRepository {

    override suspend fun getOnboardingState(): Flow<Boolean> {
        return onboardingDataStoreDataSource.getOnboardingState()
    }

    override suspend fun setOnboardingState(isComplete: Boolean) {
        onboardingDataStoreDataSource.setOnboardingState(isComplete)
    }

}