package com.chocolate.repository.datastore

interface PreferenceStorage {
    val currentOrganization: String?

    suspend fun setNameOrganization(currentOrganization: String)

    suspend fun clearCurrentOrganization()

}