package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.users.OrganizationsLocalDto

interface OrganizationsRoomDataSource {

    suspend fun insertNameOrg(nameOrg: OrganizationsLocalDto)

    suspend fun getNameOrganizations(): List<OrganizationsLocalDto>

    suspend fun deleteOrganizations(nameOrganizations: String)
}