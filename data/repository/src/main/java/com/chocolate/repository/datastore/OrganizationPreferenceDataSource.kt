package com.chocolate.repository.datastore

interface OrganizationPreferenceDataSource {
    val currentOrganization: String?

    suspend fun setNameOrganization(currentOrganization: String)

    suspend fun clearCurrentOrganization()

}