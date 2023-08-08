package com.chocolate.repository.service

interface OnboardingPreferencesDataSource {

    suspend fun shouldShowOnboarding(): Boolean

    suspend fun setOnboardingShown()

}