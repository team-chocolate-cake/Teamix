package com.chocolate.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.chocolate.repository.datasource.local.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PreferencesDataSource {
    override suspend fun getCurrentOrganizationName(): String? {
        return dataStore.data.mapLatest {
            it[ORGANIZATION_NAME]
        }.first()
    }

    override suspend fun setCurrentOrganizationName(organizationName: String) {
        dataStore.setValue(ORGANIZATION_NAME, organizationName)
    }

    override suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean) {
        dataStore.setValue(IS_FIRST_TIME, isFirstTime)
    }

    override fun isUserUsedAppOrNot(): Flow<Boolean> {
        return dataStore.data.mapLatest {
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

    override suspend fun updateAppLanguage(newLanguage: String): Boolean {
        dataStore.setValue(LOCAL_LANGUAGE, newLanguage)
        return true
    }

    override fun getLatestSelectedAppLanguage(): Flow<String> =
        dataStore.data.map {
            it[LOCAL_LANGUAGE] ?: ENGLISH
        }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStore.setValue(IS_DARK_THEME, isDarkTheme)
    }

    override fun isDarkThemeEnabled(): Flow<Boolean> {
        return dataStore.data.map {
            it[IS_DARK_THEME] ?: false
        }
    }

    override suspend fun clearMemberData() {
        dataStore.edit {
            it.remove(LOGIN_STATE)
            it.remove(MEMBER_ID)
            it.remove(ORGANIZATION_NAME)
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
        val LOGIN_STATE = booleanPreferencesKey("LOGIN_STATE")
        val MEMBER_ID = stringPreferencesKey("MEMBER_ID")
        val ORGANIZATION_NAME = stringPreferencesKey("ORGANIZATION_NAME")
        const val ENGLISH = "en"
        private val IS_DARK_THEME = booleanPreferencesKey("Is_Dark_Theme")
        private val LOCAL_LANGUAGE = stringPreferencesKey("LOCAL_LANGUAGE")
    }
}