package com.chocolate.repository.repository

import com.chocolate.entities.Organization
import com.chocolate.entities.channel.Channel
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import repositories.OrganizationsRepository
import javax.inject.Inject

class OrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: OrganizationRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : OrganizationsRepository{
    override suspend fun getOrganizationById(id: String): Organization {
        return organizationRemoteDataSource.getOrganizationById(id)
    }

    override suspend fun getOrganizaionsByMemberId(id: String): List<Organization> {
        return organizationRemoteDataSource.getOrganizaionsByMemberId(id)
    }

    override suspend fun addOrganization(organization: Organization) {
        organizationRemoteDataSource.addOrganization(organization)
    }

    override suspend fun deleteOrganizationbyId(id: String) {
        organizationRemoteDataSource.deleteOrganizationbyId(id)
    }

    override suspend fun updateOrganization(organization: Organization) {
        organizationRemoteDataSource.updateOrganization(organization)
    }

    override suspend fun addChannel(organizationId: Long, channel: Channel) {
        organizationRemoteDataSource.addChannel(organizationId , channel)
    }

    override suspend fun deleteChannelById(channelId: Long) {
        organizationRemoteDataSource.deleteChannelById(channelId)
    }

    override suspend fun saveOrganizationName(nameOrganizations: String) {
        preferencesDataSource.setOrganizationName(nameOrganizations)
    }

    override suspend fun getOrganizationName(): String {
        return preferencesDataSource.currentOrganization().toString()
    }

    override suspend fun getOrganizationImage(): String {
        return ""
    }

}