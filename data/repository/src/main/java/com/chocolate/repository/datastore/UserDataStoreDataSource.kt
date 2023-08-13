package com.chocolate.repository.datastore

interface UserDataStoreDataSource {

    suspend fun putAuthenticationData(apikey: String, email: String)

    suspend fun setUserLoginState(isComplete: Boolean)

    suspend fun getUserLoginState(): Boolean

    fun getApiKey(): String

    fun getEmail(): String

    suspend fun deleteAuthenticationData()

}