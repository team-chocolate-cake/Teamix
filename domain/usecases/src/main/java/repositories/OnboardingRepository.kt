package repositories

interface OnboardingRepository {
    suspend fun getOnboardingState(): Boolean

    suspend fun setOnboardingState(isComplete: Boolean)
}