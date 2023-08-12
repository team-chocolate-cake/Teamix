package com.chocolate.repository.repository

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.entities.server_and_organizations.Linkifiers
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.datastore.OrganizationDataStoreDataSource
import com.chocolate.repository.mappers.organizations.toEntity
import com.chocolate.repository.mappers.organizations.toLinkifiers
import com.chocolate.repository.mappers.organizations.toDefaultOrganization
import com.chocolate.repository.mappers.organizations.toCustomProfileFields
import com.chocolate.repository.mappers.organizations.toCustomEmoji
import com.chocolate.repository.service.remote.OrganizationRemoteDataSource
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: RemoteDataSource,
    private val organizationDataStoreDataSource: OrganizationDataStoreDataSource
) : ServerAndOrganizationsRepository, BaseRepository() {
    override suspend fun getServiceSettings(): ServerSettings {
        return wrapCall { organizationRemoteDataSource.getServerSettings() }.toEntity()
    }

    override suspend fun getLinkifiers(): Linkifiers {
        return wrapCall { organizationRemoteDataSource.getLinkifiers() }.toLinkifiers()
    }

    override suspend fun addLinkifiers(pattern: String, url: String): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.addLinkifiers(pattern, url) }.toDefaultOrganization()
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganization {
        return wrapCall {
            organizationRemoteDataSource.updateLinkifiers(filterId, pattern, url)
        }.toDefaultOrganization()
    }

    override suspend fun deleteLinkifier(filterId: Int): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.deleteLinkifiers(filterId) }.toDefaultOrganization()
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganization {
        return wrapCall {
            organizationRemoteDataSource.addCodePlayGround(name, language, url)
        }.toDefaultOrganization()
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.deleteCodePlayground(playGRound) }.toDefaultOrganization()
    }

    override suspend fun getAllCustomEmojis(): CustomEmoji {
        return wrapCall { organizationRemoteDataSource.getAllCustomEmojis() }.toCustomEmoji()
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.addCustomEmoji(emojiName) }.toDefaultOrganization()
    }

    override suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.deactivateCustomEmoji(emojiName) }.toDefaultOrganization()
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFields {
        return wrapCall { organizationRemoteDataSource.getAllCustomProfileFields() }.toCustomProfileFields()
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganization {
        return wrapCall { organizationRemoteDataSource.reorderCustomProfileFields(order) }.toDefaultOrganization()
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganization {
        return wrapCall {
            organizationRemoteDataSource.createCustomProfileField(name, hint, fieldType)
        }.toDefaultOrganization()
    }

    override suspend fun saveNameOrganizations(nameOrganizations: String) {
        organizationDataStoreDataSource.setNameOrganization(nameOrganizations)
    }

    override suspend fun getNameOrganizations(): String {
        return organizationDataStoreDataSource.currentOrganization.toString()
    }
}