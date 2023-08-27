package com.chocolate.repository.repository

import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: OrganizationRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : ServerAndOrganizationsRepository{
    override suspend fun getOrganizationImage(): String {
        return organizationRemoteDataSource.getServerSettings().realmIcon ?:""
    }

    override suspend fun addLinkifiers(pattern: String, url: String): Int {
        return organizationRemoteDataSource.addLinkifiers(pattern, url).id ?:-1
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Int {
        return organizationRemoteDataSource.updateLinkifiers(filterId, pattern, url).id ?:-1
    }

    override suspend fun deleteLinkifier(filterId: Int): Int {
        return organizationRemoteDataSource.deleteLinkifiers(filterId).id ?:-1
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): Int {
        return organizationRemoteDataSource.addCodePlayGround(name, language, url).id ?:-1
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): Int {
        return organizationRemoteDataSource.deleteCodePlayground(playGRound).id ?:-1
    }

    override suspend fun reorderCustomProfileFields(order: String): Int {
        return organizationRemoteDataSource.reorderCustomProfileFields(order).id ?:-1
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): Int {
        return organizationRemoteDataSource.createCustomProfileField(name, hint, fieldType).id ?:-1
    }

    override suspend fun saveOrganizationName(nameOrganizations: String) {
        preferencesDataSource.setOrganizationName(nameOrganizations)
    }

    override suspend fun getOrganizationName(): String {
        return preferencesDataSource.currentOrganization().toString()
    }
}