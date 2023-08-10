package com.chocolate.repository.service.remote

import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto
import retrofit2.Response

interface OrganizationRemoteDataSource {

    suspend fun getServerSettings(): Response<ServerSettingsDto>

    suspend fun getLinkifiers(): Response<LinkifiersDto>

    suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto>

    suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Response<DefaultOrganizationDto>

    suspend fun deleteLinkifiers(filterId: Int): Response<DefaultOrganizationDto>

    suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String,
    ): Response<DefaultOrganizationDto>

    suspend fun deleteCodePlayground(playGroundId: Int): Response<DefaultOrganizationDto>

    suspend fun getAllCustomEmojis(): Response<CustomEmojiDto>

    suspend fun addCustomEmoji(emojiName: String): Response<DefaultOrganizationDto>

    suspend fun deactivateCustomEmoji(emojiName: String): Response<DefaultOrganizationDto>

    suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto>

    suspend fun reorderCustomProfileFields(
        order: String
    ): Response<DefaultOrganizationDto>

    suspend fun createCustomProfileField(
        name: String = "",
        hint: String = "",
        fieldType: Int,
    ): Response<DefaultOrganizationDto>
}