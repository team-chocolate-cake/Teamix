package com.chocolate.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.chocolate.repository.datastore.OnboardingDataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingDataStoreDataSource {
    override suspend fun shouldShowOnboarding(): Boolean =
            dataStore.data.map { it[booleanPreferencesKey(ONBOARDING_SHOWN)] == true }.first()

    override suspend fun setOnboardingShown() {
        dataStore.edit { it[booleanPreferencesKey(ONBOARDING_SHOWN)] = true }
    }

    override suspend fun setOnboardingState(isComplete: Boolean) {
        dataStore.edit { it[booleanPreferencesKey(ONBOARDING_STATE)] = isComplete }
    }

    override suspend fun getOnboardingState(): Boolean {
        return dataStore.data.map { it[booleanPreferencesKey(ONBOARDING_STATE)] == true }.first()
    }

    companion object {
        const val ONBOARDING_SHOWN = "onboarding_shown"
        const val ONBOARDING_STATE = "ONBOARDING_STATE"
    }

}