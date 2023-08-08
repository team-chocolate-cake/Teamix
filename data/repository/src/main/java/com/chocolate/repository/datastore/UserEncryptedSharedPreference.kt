package com.chocolate.repository.datastore

interface UserEncryptedSharedPreference {

    fun putAuthenticationData(apikey: String, email: String)

    fun getApiKey(): String

    fun getEmail(): String

    fun deleteAuthenticationData()

}