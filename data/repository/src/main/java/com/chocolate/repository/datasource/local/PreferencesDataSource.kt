package com.chocolate.repository.datasource.local

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    suspend fun getCurrentOrganizationName(): String?

    suspend fun setCurrentOrganizationName(organizationName: String)

    suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean)

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun isMemberLoggedIn(): Boolean

    suspend fun setMemberLoggedIn()

    suspend fun getIdOfCurrentMember(): String?

    suspend fun saveIdOfCurrentMember(memberId: String)

    suspend fun updateAppLanguage(newLanguage: String): Boolean

    fun getLatestSelectedAppLanguage(): Flow<String>

    suspend fun setDarkTheme(isDarkTheme: Boolean)

    fun isDarkThemeEnabled(): Flow<Boolean>

    suspend fun clearMemberData()
}