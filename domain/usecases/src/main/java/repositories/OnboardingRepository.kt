package repositories

interface OnboardingRepository {
    suspend fun shouldShowOnboarding(): Boolean

    suspend fun setOnboardingShown()
}