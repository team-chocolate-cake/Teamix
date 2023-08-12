package com.chocolate.repository.datastore

interface OnboardingDataStoreDataSource {

    suspend fun shouldShowOnboarding(): Boolean

    suspend fun setOnboardingShown()

    suspend fun setOnboardingState(isComplete: Boolean)

    suspend fun getOnboardingState(): Boolean

}