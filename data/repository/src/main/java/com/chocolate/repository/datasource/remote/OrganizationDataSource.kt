package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.member.MemberDto
import com.chocolate.repository.model.dto.organization.OrganizationDto

interface OrganizationDataSource {
    suspend fun getOrganizationByName(organizationName: String): OrganizationDto?

    suspend fun createOrganization(organization: OrganizationDto)

    suspend fun deleteOrganizationByOrganizationName(organizationName: String)

    suspend fun updateOrganization(organization: OrganizationDto)

    suspend fun addMemberInOrganization(member: MemberDto, organizationName: String)
}