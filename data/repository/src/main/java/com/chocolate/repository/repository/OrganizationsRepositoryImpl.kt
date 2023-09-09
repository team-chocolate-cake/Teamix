package com.chocolate.repository.repository

import com.chocolate.entities.organization.Organization
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.InvalidOrganizationImageUrl
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.chocolate.repository.mappers.toEntity
import repositories.OrganizationsRepository
import javax.inject.Inject

class OrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: OrganizationRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : OrganizationsRepository {

    override suspend fun getOrganizationByName(organizationName: String): Organization? {
        return organizationRemoteDataSource.getOrganizationByName(organizationName)?.toEntity()
    }

    override suspend fun saveOrganizationName(nameOrganizations: String) {
        preferencesDataSource.setCurrentOrganizationName(nameOrganizations)
    }

    override suspend fun getOrganizationName(): String {
        return preferencesDataSource.getCurrentOrganizationName() ?: throw EmptyOrganizationNameException
    }

    override suspend fun getOrganizationImage(): String {
        return organizationRemoteDataSource.getOrganizationByName(getOrganizationName())?.imageUrl
            ?: throw InvalidOrganizationImageUrl
    }

}
