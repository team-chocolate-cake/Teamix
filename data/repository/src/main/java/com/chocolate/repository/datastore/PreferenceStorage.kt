package com.chocolate.repository.datastore

interface PreferenceStorage {
    val nameOrganization: String?

    suspend fun setNameOrganization(nameOrganization: String)

}