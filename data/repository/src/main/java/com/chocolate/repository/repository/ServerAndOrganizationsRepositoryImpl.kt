package com.chocolate.repository.repository

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.entities.server_and_organizations.Linkifiers
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.datastore.PreferencesDataSource
import com.chocolate.repository.mappers.organizations.toCustomEmoji
import com.chocolate.repository.mappers.organizations.toCustomProfileFields
import com.chocolate.repository.mappers.organizations.toDefaultOrganization
import com.chocolate.repository.mappers.organizations.toEntity
import com.chocolate.repository.mappers.organizations.toLinkifiers
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : ServerAndOrganizationsRepository{
    override suspend fun getServiceSettings(): ServerSettings {
        return organizationRemoteDataSource.getServerSettings().toEntity()
    }

    override suspend fun getLinkifiers(): Linkifiers {
        return organizationRemoteDataSource.getLinkifiers().toLinkifiers()
    }

    override suspend fun addLinkifiers(pattern: String, url: String): DefaultOrganization {
        return organizationRemoteDataSource.addLinkifiers(pattern, url).toDefaultOrganization()
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganization {
        return organizationRemoteDataSource.updateLinkifiers(filterId, pattern, url).toDefaultOrganization()
    }

    override suspend fun deleteLinkifier(filterId: Int): DefaultOrganization {
        return organizationRemoteDataSource.deleteLinkifiers(filterId).toDefaultOrganization()
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganization {
        return organizationRemoteDataSource.addCodePlayGround(name, language, url).toDefaultOrganization()
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): DefaultOrganization {
        return organizationRemoteDataSource.deleteCodePlayground(playGRound).toDefaultOrganization()
    }

    override suspend fun getAllCustomEmojis(): CustomEmoji {
        return organizationRemoteDataSource.getAllCustomEmojis().toCustomEmoji()
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganization {
        return organizationRemoteDataSource.addCustomEmoji(emojiName).toDefaultOrganization()
    }

    override suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization {
        return organizationRemoteDataSource.deactivateCustomEmoji(emojiName).toDefaultOrganization()
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFields {
        return organizationRemoteDataSource.getAllCustomProfileFields().toCustomProfileFields()
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganization {
        return organizationRemoteDataSource.reorderCustomProfileFields(order).toDefaultOrganization()
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganization {
        return organizationRemoteDataSource.createCustomProfileField(name, hint, fieldType).toDefaultOrganization()
    }

    override suspend fun saveNameOrganizations(nameOrganizations: String) {
        preferencesDataSource.setOrganizationName(nameOrganizations)
    }

    override suspend fun getNameOrganizations(): String {
        return preferencesDataSource.currentOrganization().toString()
    }
}