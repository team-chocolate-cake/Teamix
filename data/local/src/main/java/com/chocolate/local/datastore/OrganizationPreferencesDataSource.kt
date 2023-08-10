package com.chocolate.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.chocolate.repository.datastore.OrganizationDataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class OrganizationPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OrganizationDataStoreDataSource {

    private object PreferencesKeys {
        val NAME_ORGANIZATION = stringPreferencesKey("CURRENT_USERNAME_ID")
    }

    override val currentOrganization: String?
        get() = runBlocking {  dataStore.data.map { it[PreferencesKeys.NAME_ORGANIZATION] }.first()}

    override suspend fun setNameOrganization(currentOrganization: String) {
        dataStore.setValue(PreferencesKeys.NAME_ORGANIZATION, currentOrganization)
    }

    override suspend fun clearCurrentOrganization() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }
}