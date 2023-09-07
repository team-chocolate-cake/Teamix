package com.chocolate.repository.datastore.remote

import com.chocolate.entities.Organization

interface OrganizationRemoteDataSource {
    suspend fun getOrganizationByName(organizationName: String): Organization?
    suspend fun createOrganization(organization: Organization)
    suspend fun deleteOrganizationByOrganizationName(organizationName: String)
    suspend fun updateOrganization(organization: Organization)
}