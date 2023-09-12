package com.chocolate.local.datastore

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.chocolate.repository.datastore.local.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val sharedPreferences: SharedPreferences,
) : PreferencesDataSource {
    override fun getCurrentOrganizationName(): String? {
        return sharedPreferences.getString(ORGANIZATION_NAME, null)
    }

    override suspend fun setCurrentOrganizationName(organizationName: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ORGANIZATION_NAME, organizationName)
        editor.apply()
    }

    override suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean) {
        dataStore.setValue(IS_FIRST_TIME, isFirstTime)
    }

    override suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return dataStore.data.map {
            it[(IS_FIRST_TIME)] ?: false
        }
    }

    override suspend fun isMemberLoggedIn(): Boolean {
        return dataStore.data.mapLatest {
            it[LOGIN_STATE] ?: false
        }.first()
    }

    override suspend fun setMemberLoggedIn() {
        dataStore.setValue(LOGIN_STATE, true)
    }

    override suspend fun getIdOfCurrentMember(): String? {
        return dataStore.data.mapLatest {
            it[MEMBER_ID]
        }.first()
    }

    override suspend fun saveIdOfCurrentMember(memberId: String) {
        dataStore.setValue(MEMBER_ID, memberId)
    }

    override suspend fun upsertAppLanguage(newLanguage: String): Boolean {
        dataStore.setValue(LOCAL_LANGUAGE, newLanguage)
        return true
    }

    override suspend fun getLastSelectedAppLanguage(): Flow<String> =
        dataStore.data.map {
            it[(LOCAL_LANGUAGE)] ?: ENGLISH
        }

    override suspend fun setDarkThemeValue(isDarkTheme: Boolean) {
        dataStore.setValue(IS_DARK_THEME, isDarkTheme)
    }

    override suspend fun isDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
    }

    override suspend fun isInDarkThemeFlow(): Flow<Boolean> {
        return dataStore.data.map {
            it[(IS_DARK_THEME)] ?: false
        }
    }

    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T,
    ) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    companion object {
        private val IS_FIRST_TIME = booleanPreferencesKey("IS_FIRST_TIME")
        const val API_KEY = "API_KEY"
        val LOGIN_STATE = booleanPreferencesKey("LOGIN_STATE")
        val MEMBER_ID = stringPreferencesKey("MEMBER_ID")
        const val ORGANIZATION_NAME = "CURRENT_USERNAME_ID"
        const val ENGLISH = "en"
        const val DARK_THEME = "APP_DARK_THEME"
        private val IS_DARK_THEME = booleanPreferencesKey("Is_Dark_Theme")
        private val LOCAL_LANGUAGE = stringPreferencesKey("LOCAL_LANGUAGE")
    }
}