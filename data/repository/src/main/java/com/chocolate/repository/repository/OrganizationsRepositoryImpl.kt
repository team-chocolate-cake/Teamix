package com.chocolate.repository.repository

import com.chocolate.entities.Organization
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.OrganizationDataSource
import repositories.OrganizationsRepository
import javax.inject.Inject

class OrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: OrganizationDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : OrganizationsRepository {

    override suspend fun getOrganizationByName(organizationName: String): Organization? =
        organizationRemoteDataSource.getOrganizationByName(organizationName)

    override suspend fun saveOrganizationName(nameOrganizations: String) {
        preferencesDataSource.setOrganizationName(nameOrganizations)
    }

/*    override suspend fun getOrganizationName(): String {
        return preferencesDataSource.currentOrganization() ?: ""
    }

    override suspend fun getOrganizationImage(): String {
        return organizationRemoteDataSource.getOrganizationByName(getOrganizationName())?.imageUrl
            ?: ""
    }*/

}