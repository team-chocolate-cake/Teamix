package com.chocolate.repository.repository

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.entities.server_and_organizations.Linkifiers
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.datastore.DataStoreDataSource
import com.chocolate.repository.mappers.organizations.toEntity
import com.chocolate.repository.mappers.organizations.toLinkifiers
import com.chocolate.repository.mappers.organizations.toDefaultOrganization
import com.chocolate.repository.mappers.organizations.toCustomProfileFields
import com.chocolate.repository.mappers.organizations.toCustomEmoji
import com.chocolate.repository.service.remote.OrganizationRemoteDataSource
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: OrganizationRemoteDataSource,
    private val dataStoreDataSource: DataStoreDataSource
) : ServerAndOrganizationsRepository, BaseRepository() {
    override suspend fun getServiceSettings(): ServerSettings {
        return wrapApiCall { organizationRemoteDataSource.getServerSettings() }.toEntity()
    }

    override suspend fun getLinkifiers(): Linkifiers {
        return wrapApiCall { organizationRemoteDataSource.getLinkifiers() }.toLinkifiers()
    }

    override suspend fun addLinkifiers(pattern: String, url: String): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.addLinkifiers(pattern, url) }.toDefaultOrganization()
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganization {
        return wrapApiCall {
            organizationRemoteDataSource.updateLinkifiers(filterId, pattern, url)
        }.toDefaultOrganization()
    }

    override suspend fun deleteLinkifier(filterId: Int): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.deleteLinkifiers(filterId) }.toDefaultOrganization()
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganization {
        return wrapApiCall {
            organizationRemoteDataSource.addCodePlayGround(name, language, url)
        }.toDefaultOrganization()
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.deleteCodePlayground(playGRound) }.toDefaultOrganization()
    }

    override suspend fun getAllCustomEmojis(): CustomEmoji {
        return wrapApiCall { organizationRemoteDataSource.getAllCustomEmojis() }.toCustomEmoji()
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.addCustomEmoji(emojiName) }.toDefaultOrganization()
    }

    override suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.deactivateCustomEmoji(emojiName) }.toDefaultOrganization()
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFields {
        return wrapApiCall { organizationRemoteDataSource.getAllCustomProfileFields() }.toCustomProfileFields()
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganization {
        return wrapApiCall { organizationRemoteDataSource.reorderCustomProfileFields(order) }.toDefaultOrganization()
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganization {
        return wrapApiCall {
            organizationRemoteDataSource.createCustomProfileField(name, hint, fieldType)
        }.toDefaultOrganization()
    }

    override suspend fun saveNameOrganizations(nameOrganizations: String) {
        dataStoreDataSource.setNameOrganization(nameOrganizations)
    }

    override suspend fun getNameOrganizations(): String {
        return dataStoreDataSource.currentOrganization.toString()
    }
}