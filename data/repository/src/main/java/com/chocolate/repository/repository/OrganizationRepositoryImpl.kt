package com.chocolate.repository.repository

import com.chocolate.entities.Organization
import com.chocolate.entities.utils.EmptyOrganizationNameException
import com.chocolate.entities.utils.InvalidOrganizationImageUrl
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.OrganizationDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toRemote
import repositories.OrganizationRepository
import javax.inject.Inject

class OrganizationRepositoryImpl @Inject constructor(
    private val organizationDataSource: OrganizationDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : OrganizationRepository {
    override suspend fun getOrganizationByName(organizationName: String): Organization? {
        return organizationDataSource.getOrganizationByName(organizationName)?.toEntity()
    }

    override suspend fun saveOrganizationName(nameOrganizations: String) {
        preferencesDataSource.setCurrentOrganizationName(nameOrganizations)
    }

    override suspend fun getOrganizationName(): String {
        return preferencesDataSource.getCurrentOrganizationName() ?: throw EmptyOrganizationNameException
    }

    override suspend fun getOrganizationImage(): String {
        return organizationDataSource.getOrganizationByName(getOrganizationName())?.imageUrl
            ?: throw InvalidOrganizationImageUrl
    }

    override suspend fun createOrganization(organization: Organization) {
        organizationDataSource.createOrganization(organization.toRemote())
    }
}
