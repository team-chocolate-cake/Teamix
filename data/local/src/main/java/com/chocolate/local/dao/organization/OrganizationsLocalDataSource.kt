package com.chocolate.local.dao.organization

import com.chocolate.repository.model.localDto.users.OrganizationsLocalDto
import com.chocolate.repository.service.local.OrganizationsRoomDataSource
import javax.inject.Inject

class OrganizationsLocalDataSource @Inject constructor(
    private val organizationsDao: OrganizationsDao
) : OrganizationsRoomDataSource{
    override suspend fun insertNameOrg(nameOrg: OrganizationsLocalDto) {
        organizationsDao.upsertNameOrg(nameOrg)
    }

    override suspend fun getNameOrganizations(): List<OrganizationsLocalDto> {
        return organizationsDao.getNameOrganizations()
    }

    override suspend fun deleteOrganizations(nameOrganizations: String) {
        organizationsDao.deleteOrganizations(nameOrganizations)
    }
}