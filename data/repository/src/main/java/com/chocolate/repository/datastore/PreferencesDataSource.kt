package com.chocolate.repository.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    val currentOrganization: String?

    suspend fun setNameOrganization(currentOrganization: String)

    suspend fun deleteDataStore()

    suspend fun setUserLoginState(isComplete: Boolean)

    val currentUserLoginState: Flow<Boolean>

    suspend fun putOnboardingState(isFirstTime: Boolean)

    suspend fun getOnboardingState(): Boolean

    suspend fun putAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    suspend fun deleteAuthenticationData()

    suspend fun updateAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): String

    suspend fun updateDarkTheme(isDarkTheme: Boolean): Boolean

    suspend fun isDarkThemeEnabled(): Boolean
}