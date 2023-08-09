package com.chocolate.remote.server_and_organizations

import com.chocolate.remote.server_and_organizations.service.OrganizationService
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto
import com.chocolate.repository.repository.BaseRepository
import com.chocolate.repository.service.remote.OrganizationRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class OrganizationsRetrofitDataSource @Inject constructor(
    private val organizationService: OrganizationService
): OrganizationRemoteDataSource, BaseRepository() {
    override suspend fun getServerSettings(): Response<ServerSettingsDto> {
        return organizationService.getServerSettings()
    }

    override suspend fun getLinkifiers(): Response<LinkifiersDto> {
        return organizationService.getLinkifiers()
    }

    override suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        return organizationService.addLinkifiers(pattern, url)
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        return organizationService.updateLinkifiers(filterId, pattern, url)
    }

    override suspend fun deleteLinkifiers(filterId: Int): Response<DefaultOrganizationDto> {
        return organizationService.deleteLinkifiers(filterId)

    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        return organizationService.addCodePlayGround(name, language, url)
    }

    override suspend fun deleteCodePlayground(playGroundId: Int): Response<DefaultOrganizationDto> {
        return organizationService.deleteCodePlayground(playGroundId)
    }

    override suspend fun getAllCustomEmojis(): Response<CustomEmojiDto> {
        return organizationService.getAllCustomEmojis()
    }

    override suspend fun addCustomEmoji(emojiName: String): Response<DefaultOrganizationDto> {
        return organizationService.addCustomEmoji(emojiName)
    }

    override suspend fun deactivateCustomEmoji(emojiName: String): Response<DefaultOrganizationDto> {
        return organizationService.deactivateCustomEmoji(emojiName)
    }

    override suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto> {
        return organizationService.getAllCustomProfileFields()
    }

    override suspend fun reorderCustomProfileFields(order: String): Response<DefaultOrganizationDto> {
        return organizationService.reorderCustomProfileFields(order)
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): Response<DefaultOrganizationDto> {
        return organizationService.createCustomProfileField(name, hint, fieldType)
    }
}