package com.chocolate.repository.service.local

import com.chocolate.repository.dto.local.users.OrganizationsLocalDto

interface OrganizationsLocalDataSource {

    suspend fun insertNameOrg(nameOrg: OrganizationsLocalDto)

    suspend fun getNameOrganizations(): List<OrganizationsLocalDto>

    suspend fun deleteOrganizations(nameOrganizations: OrganizationsLocalDto)
}