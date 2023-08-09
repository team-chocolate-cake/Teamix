package com.chocolate.repository.datastore

interface UserDataStoreDataSource {

    fun putAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    fun deleteAuthenticationData()

}