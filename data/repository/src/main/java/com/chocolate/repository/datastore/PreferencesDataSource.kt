package com.chocolate.repository.datastore

interface PreferencesDataSource {

     fun currentOrganization(): String?

    suspend fun setOrganizationName(currentOrganization: String)

    suspend fun setUserLoginState(isComplete: Boolean)

    suspend fun getCurrentUserLoginState(): Boolean

    suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean)

    suspend fun checkIfUserUsedAppOrNot(): Boolean

    suspend fun setAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    suspend fun deleteAuthenticationData()

    suspend fun upsertAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): String

    suspend fun setDarkThemeValue(isDarkTheme: Boolean): Boolean

    suspend fun isDarkThemeEnabled(): Boolean
}