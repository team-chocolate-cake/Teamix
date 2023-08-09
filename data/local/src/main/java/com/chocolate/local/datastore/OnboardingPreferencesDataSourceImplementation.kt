package com.chocolate.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.chocolate.repository.service.OnboardingPreferencesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class OnboardingPreferencesDataSourceImplementation @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingPreferencesDataSource {
    override suspend fun shouldShowOnboarding(): Boolean =
        runBlocking {
            dataStore.data.map { it[booleanPreferencesKey(ONBOARDING_SHOWN)] == true }.first()
        }

    override suspend fun setOnboardingShown() {
        dataStore.edit { it[booleanPreferencesKey(ONBOARDING_SHOWN)] = true }
    }

    companion object {
        const val ONBOARDING_SHOWN = "onboarding_shown"
    }

}