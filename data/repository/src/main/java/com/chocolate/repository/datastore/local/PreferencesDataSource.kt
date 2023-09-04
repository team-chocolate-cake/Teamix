package com.chocolate.repository.datastore.local

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

     fun currentOrganization(): String?

    suspend fun setOrganizationName(currentOrganization: String)

    suspend fun setUserLoginState(isComplete: Boolean)

    suspend fun getCurrentUserLoginState(): Flow<Boolean>

    suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean)

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun setAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    suspend fun deleteAuthenticationData()

    suspend fun upsertAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): Flow<String>

    suspend fun setDarkThemeValue(isDarkTheme: Boolean)

    suspend fun isDarkThemeEnabled(): Boolean
    suspend fun isInDarkThemeFlow():Flow<Boolean>
}