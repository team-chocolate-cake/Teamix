package com.chocolate.local.dao.organization

import com.chocolate.repository.dto.local.users.OrganizationsLocalDto
import com.chocolate.repository.service.local.OrganizationsLocalDataSource
import javax.inject.Inject

class OrganizationsLocalDataSourceImpl @Inject constructor(
    private val organizationsDao: OrganizationsDao
) : OrganizationsLocalDataSource{
    override suspend fun insertNameOrg(nameOrg: OrganizationsLocalDto) {
        organizationsDao.insertNameOrg(nameOrg)
    }

    override suspend fun getNameOrganizations(): List<OrganizationsLocalDto> {
        return organizationsDao.getNameOrganizations()
    }

    override suspend fun deleteOrganizations(nameOrganizations: OrganizationsLocalDto) {
        organizationsDao.deleteOrganizations(nameOrganizations)
    }
}