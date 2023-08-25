package com.chocolate.local.datastore

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.chocolate.local.datastore.util.get
import com.chocolate.repository.datastore.PreferencesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val sharedPreferences: SharedPreferences
) : PreferencesDataSource {
    override fun currentOrganization(): String? {
        return sharedPreferences.getString(NAME_ORGANIZATION, null)
    }

    override suspend fun setOrganizationName(currentOrganization: String) {
        val editor = sharedPreferences.edit()
        editor.putString(NAME_ORGANIZATION, currentOrganization)
        editor.apply()
    }

    override suspend fun setUserLoginState(isComplete: Boolean) {
        dataStore.setValue(LOGIN_STATE, isComplete)
    }

    override suspend fun getCurrentUserLoginState(): Boolean {
        return dataStore.get()[LOGIN_STATE] ?: false
    }

    override suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean) {
        dataStore.setValue(IS_FIRST_TIME, isFirstTime)
    }

    override suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return dataStore.data.map {
            it[(IS_FIRST_TIME)] ?: false
        }
    }

    override suspend fun setAuthenticationData(apikey: String, email: String) {
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
        dataStore.edit { preferences -> preferences.remove(LOGIN_STATE) }
        sharedPreferences.edit().clear().apply()
    }

    override suspend fun upsertAppLanguage(newLanguage: String): Boolean {
        val editor = sharedPreferences.edit()
        editor.putString(LANGUAGE, newLanguage)
        return editor.commit()
    }

    override suspend fun getLastSelectedAppLanguage(): String =
        sharedPreferences.getString(LANGUAGE, null) ?: ENGLISH

    override suspend fun setDarkThemeValue(isDarkTheme: Boolean): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean(DARK_THEME, isDarkTheme)
        return editor.commit()
    }

    override suspend fun isDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
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
        private val IS_FIRST_TIME = booleanPreferencesKey("IS_FIRST_TIME")
        private val LOGIN_STATE = booleanPreferencesKey("LOGIN_STATE")
        const val API_KEY = "API_KEY"
        const val EMAIL = "EMAIL"
        const val NAME_ORGANIZATION = "CURRENT_USERNAME_ID"
        const val LANGUAGE = "APP_LANGUAGE"
        const val ENGLISH = "en"
        const val DARK_THEME = "APP_DARK_THEME"
    }
}