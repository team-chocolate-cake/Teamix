package com.chocolate.local.dao.organization

import com.chocolate.repository.dto.local.users.OrganizationsLocalDto
import com.chocolate.repository.service.local.OrganizationsLocalDataSource
import javax.inject.Inject

class OrganizationsLocalDataSourceImplementation @Inject constructor(
    private val organizationsDao: OrganizationsDao
) : OrganizationsLocalDataSource{
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