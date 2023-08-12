package com.chocolate.repository.datastore

interface UserDataStoreDataSource {

    fun putAuthenticationData(apikey: String, email: String)

    fun setUserLoginState(isComplete: Boolean)

    fun getUserLoginState(): Boolean

    fun getApiKey(): String

    fun getEmail(): String

    fun deleteAuthenticationData()

}