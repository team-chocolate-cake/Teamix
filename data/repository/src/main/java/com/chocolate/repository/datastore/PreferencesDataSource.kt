package com.chocolate.repository.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    suspend fun setOnboardingState(isComplete: Boolean)

    suspend fun getOnboardingState(): Flow<Boolean>

    val currentOrganization: String?

    suspend fun setNameOrganization(currentOrganization: String)

    suspend fun clearCurrentOrganization()

    suspend fun setUserLoginState(isComplete: Boolean)

    val currentUserLoginState: Flow<Boolean>

    suspend fun putAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    suspend fun deleteAuthenticationData()
}