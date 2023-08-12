package com.chocolate.repository.datastore

import kotlinx.coroutines.flow.Flow

interface OnboardingDataStoreDataSource {

    suspend fun setOnboardingState(isComplete: Boolean)

    suspend fun getOnboardingState(): Flow<Boolean>

}