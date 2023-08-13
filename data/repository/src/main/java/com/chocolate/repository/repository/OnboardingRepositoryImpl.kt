package com.chocolate.repository.repository

import com.chocolate.repository.datastore.DataStoreDataSource
import kotlinx.coroutines.flow.Flow
import repositories.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : OnboardingRepository {

    override suspend fun getOnboardingState(): Flow<Boolean> {
        return dataStoreDataSource.getOnboardingState()
    }

    override suspend fun setOnboardingState(isComplete: Boolean) {
        dataStoreDataSource.setOnboardingState(isComplete)
    }

}