package com.chocolate.repository.datastore

interface OrganizationDataStoreDataSource {
    val currentOrganization: String?

    suspend fun setNameOrganization(currentOrganization: String)

    suspend fun clearCurrentOrganization()

}