package com.chocolate.repository.datastore.local

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    fun getCurrentOrganizationName(): String?

    suspend fun setCurrentOrganizationName(organizationName: String)

    suspend fun setUserUsedAppForFirstTime(isFirstTime: Boolean)

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun isMemberLoggedIn(): Boolean

    suspend fun setMemberLoggedIn()

    suspend fun getIdOfCurrentMember(): String?

    suspend fun saveIdOfCurrentMember(memberId: String)

    suspend fun upsertAppLanguage(newLanguage: String): Boolean

    suspend fun getLastSelectedAppLanguage(): Flow<String>

    suspend fun setDarkThemeValue(isDarkTheme: Boolean)

    suspend fun isDarkThemeEnabled(): Boolean

    suspend fun isInDarkThemeFlow(): Flow<Boolean>
}