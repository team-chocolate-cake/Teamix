package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto

interface OrganizationRemoteDataSource {

    suspend fun getServerSettings(): ServerSettingsDto

    suspend fun getLinkifiers(): LinkifiersDto

    suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): DefaultOrganizationDto

    suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganizationDto

    suspend fun deleteLinkifiers(filterId: Int): DefaultOrganizationDto

    suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String,
    ): DefaultOrganizationDto

    suspend fun deleteCodePlayground(playGroundId: Int): DefaultOrganizationDto

    suspend fun getAllCustomEmojis(): CustomEmojiDto

    suspend fun addCustomEmoji(emojiName: String): DefaultOrganizationDto

    suspend fun deactivateCustomEmoji(emojiName: String): DefaultOrganizationDto

    suspend fun getAllCustomProfileFields(): CustomProfileFieldsDto

    suspend fun reorderCustomProfileFields(
        order: String
    ): DefaultOrganizationDto

    suspend fun createCustomProfileField(
        name: String = "",
        hint: String = "",
        fieldType: Int,
    ): DefaultOrganizationDto
}