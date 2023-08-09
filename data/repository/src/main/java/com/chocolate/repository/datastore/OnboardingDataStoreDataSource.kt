package com.chocolate.repository.datastore

interface OnboardingDataStoreDataSource {

    suspend fun shouldShowOnboarding(): Boolean

    suspend fun setOnboardingShown()

}