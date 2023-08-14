package com.chocolate.repository.repository

import com.chocolate.repository.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import repositories.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : OnboardingRepository {

    override suspend fun getOnboardingState(): Flow<Boolean> {
        return preferencesDataSource.getOnboardingState()
    }

    override suspend fun setOnboardingState(isComplete: Boolean) {
        preferencesDataSource.setOnboardingState(isComplete)
    }

}