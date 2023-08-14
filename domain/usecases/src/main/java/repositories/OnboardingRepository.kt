package repositories

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    suspend fun getOnboardingState(): Flow<Boolean>

    suspend fun setOnboardingState(isComplete: Boolean)
}