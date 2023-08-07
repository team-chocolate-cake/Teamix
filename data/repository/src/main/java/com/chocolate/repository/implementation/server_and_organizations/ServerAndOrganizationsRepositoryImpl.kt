package com.chocolate.repository.implementation.server_and_organizations

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.entities.server_and_organizations.LinkifiersEntity
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.mappers.organizations.toEntity
import com.chocolate.repository.service.remote.OrganizationDataSource
import repositories.server_and_organizations.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationDataSource: OrganizationDataSource
) : ServerAndOrganizationsRepository, BaseRepository() {
    override suspend fun getServiceSettings(): ServerSettings {
        return wrapApiCall { organizationDataSource.getServerSettings() }.toEntity()
    }

    override suspend fun getLinkifiers(): LinkifiersEntity {
        return wrapApiCall { organizationDataSource.getLinkifiers() }.toEntity()
    }

    override suspend fun addLinkifiers(pattern: String, url: String): DefaultOrganization {
        return wrapApiCall { organizationDataSource.addLinkifiers(pattern, url) }.toEntity()
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganization {
        return wrapApiCall {
            organizationDataSource.updateLinkifiers(filterId, pattern, url)
        }.toEntity()
    }

    override suspend fun deleteLinkifier(filterId: Int): DefaultOrganization {
        return wrapApiCall { organizationDataSource.deleteLinkifiers(filterId) }.toEntity()
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganization {
        return wrapApiCall {
            organizationDataSource.addCodePlayGround(name, language, url)
        }.toEntity()
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): DefaultOrganization {
        return wrapApiCall { organizationDataSource.deleteCodePlayground(playGRound) }.toEntity()
    }

    override suspend fun getAllCustomEmojis(): CustomEmoji {
        return wrapApiCall { organizationDataSource.getAllCustomEmojis() }.toEntity()
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapApiCall { organizationDataSource.addCustomEmoji(emojiName) }.toEntity()
    }

    override suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization {
        return wrapApiCall { organizationDataSource.deactivateCustomEmoji(emojiName) }.toEntity()
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFields {
        return wrapApiCall { organizationDataSource.getAllCustomProfileFields() }.toEntity()
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganization {
        return wrapApiCall { organizationDataSource.reorderCustomProfileFields(order) }.toEntity()
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganization {
        return wrapApiCall {
            organizationDataSource.createCustomProfileField(name, hint, fieldType)
        }.toEntity()
    }
}