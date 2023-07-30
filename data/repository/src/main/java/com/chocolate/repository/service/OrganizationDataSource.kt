package com.chocolate.repository.service

import com.chocolate.repository.dto.server_and_organizations.response.AddLinkifiersOrCodePlayGroundDto
import com.chocolate.repository.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.dto.server_and_organizations.response.ServerSettingsDto
import com.chocolate.repository.dto.server_and_organizations.response.UpdateOrRemoveDto
import retrofit2.Response

interface OrganizationDataSource {

    suspend fun getServiceSettings(): Response<ServerSettingsDto>

    suspend fun getLinkifiers(): Response<LinkifiersDto>

    suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): Response<AddLinkifiersOrCodePlayGroundDto>

    suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Response<UpdateOrRemoveDto>

    suspend fun deleteLinkifiers(filterId: Int): Response<UpdateOrRemoveDto>

    suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String,
    ): Response<AddLinkifiersOrCodePlayGroundDto>

    suspend fun deleteCodePlayground(playGroundId: Int): Response<UpdateOrRemoveDto>

    suspend fun getAllCustomEmojis(): Response<CustomEmojiDto>

    suspend fun addCustomEmoji(emojiName: String): Response<UpdateOrRemoveDto>

    suspend fun deactivateCustomEmoji(emojiName: String): Response<UpdateOrRemoveDto>

    suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto>

    suspend fun reorderCustomProfileFields(
        order: String
    ): Response<UpdateOrRemoveDto>

    suspend fun createCustomProfileField(
        name: String = "",
        hint: String = "",
        fieldType: Int,
    ): Response<AddLinkifiersOrCodePlayGroundDto>

}
