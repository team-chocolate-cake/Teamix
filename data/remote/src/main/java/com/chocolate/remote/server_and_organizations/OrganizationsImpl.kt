package com.chocolate.remote.server_and_organizations

import com.chocolate.remote.server_and_organizations.service.OrganizationService
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.dto.remote.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.dto.remote.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.dto.remote.server_and_organizations.response.ServerSettingsDto
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.remote.OrganizationDataSource
import retrofit2.Response
import javax.inject.Inject

class OrganizationsImpl @Inject constructor(
    private val organizationService: OrganizationService
): OrganizationDataSource, BaseRepository() {
    override suspend fun getServerSettings(): Response<ServerSettingsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getLinkifiers(): Response<LinkifiersDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLinkifiers(filterId: Int): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCodePlayground(playGroundId: Int): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCustomEmojis(): Response<CustomEmojiDto> {
        TODO("Not yet implemented")
    }

    override suspend fun addCustomEmoji(emojiName: String): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateCustomEmoji(emojiName: String): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun reorderCustomProfileFields(order: String): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): Response<DefaultOrganizationDto> {
        TODO("Not yet implemented")
    }
}