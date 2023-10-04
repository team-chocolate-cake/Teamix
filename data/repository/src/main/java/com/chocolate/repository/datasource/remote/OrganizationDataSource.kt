package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.MemberDto
import com.chocolate.repository.model.dto.OrganizationDto

interface OrganizationDataSource {
    suspend fun getOrganizationByName(organizationName: String): OrganizationDto?

    suspend fun createOrganization(organization: OrganizationDto)

    suspend fun deleteOrganizationByOrganizationName(organizationName: String)

    suspend fun updateOrganization(organization: OrganizationDto)

    suspend fun addMemberInOrganization(member: MemberDto, organizationName: String)
}