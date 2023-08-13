package com.chocolate.local.datastore

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.chocolate.repository.datastore.DataStoreDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val sharedPreferences: SharedPreferences

) : DataStoreDataSource {

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


    override val currentOrganization: String?
        get() = runBlocking { dataStore.data.map { it[NAME_ORGANIZATION] }.first() }

    override suspend fun setNameOrganization(currentOrganization: String) {
        dataStore.setValue(NAME_ORGANIZATION, currentOrganization)
    }

    override suspend fun clearCurrentOrganization() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun setUserLoginState(isComplete: Boolean) {
        dataStore.setValue(LOGIN_STATE, isComplete)
    }

    override val currentUserLoginState: Flow<Boolean>
        get() = dataStore.data.map { it[LOGIN_STATE] ?: false }

    override suspend fun putAuthenticationData(apikey: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(API_KEY, apikey)
        editor.putString(EMAIL, email)
        editor.apply()
    }

    override fun getApiKey(): String {
        return sharedPreferences.getString(API_KEY, null) ?: ""
    }

    override fun getEmail(): String {
        return sharedPreferences.getString(EMAIL, null) ?: ""
    }

    override suspend fun deleteAuthenticationData() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }

    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    companion object {
        private val NAME_ORGANIZATION = stringPreferencesKey("CURRENT_USERNAME_ID")
        private val LOGIN_STATE = booleanPreferencesKey("LOGIN_STATE")
        const val ONBOARDING_STATE = "ONBOARDING_STATE"
        const val API_KEY = "API_KEY"
        const val EMAIL = "EMAIL"
    }
}