package repositories.onboarding

interface OnboardingRepository {
    suspend fun shouldShowOnboarding(): Boolean

    suspend fun setOnboardingShown()
}