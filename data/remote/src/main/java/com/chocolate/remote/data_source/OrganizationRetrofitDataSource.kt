package com.chocolate.remote.data_source

import com.chocolate.remote.api.OrganizationService
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto
import javax.inject.Inject

class OrganizationRetrofitDataSource @Inject constructor(
    private val organizationService: OrganizationService
) : OrganizationRemoteDataSource {

    override suspend fun getServerSettings(): ServerSettingsDto {
        return wrapApiCall {
            organizationService.getServerSettings()
        }
    }

    override suspend fun getLinkifiers(): LinkifiersDto {
        return wrapApiCall {
            organizationService.getLinkifiers()
        }
    }

    override suspend fun addLinkifiers(
        pattern: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addLinkifiers(pattern, url)
        }
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.updateLinkifiers(filterId, pattern, url)
        }
    }

    override suspend fun deleteLinkifiers(filterId: Int): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deleteLinkifiers(filterId)
        }
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addCodePlayGround(name, language, url)
        }
    }

    override suspend fun deleteCodePlayground(playGroundId: Int): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deleteCodePlayground(playGroundId)
        }
    }

    override suspend fun getAllCustomEmojis(): CustomEmojiDto {
        return wrapApiCall {
            organizationService.getAllCustomEmojis()
        }
    }

    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.addCustomEmoji(emojiName)
        }
    }

    override suspend fun deactivateCustomEmoji(emojiName: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.deactivateCustomEmoji(emojiName)
        }
    }

    override suspend fun getAllCustomProfileFields(): CustomProfileFieldsDto {
        return wrapApiCall {
            organizationService.getAllCustomProfileFields()
        }
    }

    override suspend fun reorderCustomProfileFields(order: String): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.reorderCustomProfileFields(order)
        }
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganizationDto {
        return wrapApiCall {
            organizationService.createCustomProfileField(name, hint, fieldType)
        }
    }
}