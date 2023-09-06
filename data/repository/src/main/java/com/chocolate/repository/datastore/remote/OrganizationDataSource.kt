package com.chocolate.repository.datastore.remote

import com.chocolate.entities.Organization
import com.chocolate.entities.channel.Channel
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.model.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.model.dto.server_and_organizations.response.DefaultOrganizationDto
import com.chocolate.repository.model.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto

interface OrganizationDataSource {
    suspend fun getOrganizationByName(organizationName: String): Organization?
    suspend fun addOrganization(organization: Organization)
    suspend fun deleteOrganizationByOrganizationName(organizationName: String)
    suspend fun updateOrganization(organization: Organization)
}