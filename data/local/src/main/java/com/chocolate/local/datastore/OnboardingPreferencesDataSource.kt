package com.chocolate.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.chocolate.repository.datastore.OnboardingDataStoreDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class OnboardingPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingDataStoreDataSource {

    override suspend fun setOnboardingState(isComplete: Boolean) {
        runBlocking {
            dataStore.edit { it[booleanPreferencesKey(ONBOARDING_STATE)] = isComplete }
        }
    }

    override suspend fun getOnboardingState(): Flow<Boolean> {
        return dataStore.data.map {
            it[booleanPreferencesKey(ONBOARDING_STATE)] ?: false
        }
    }

    companion object {
        const val ONBOARDING_STATE = "ONBOARDING_STATE"
    }

}