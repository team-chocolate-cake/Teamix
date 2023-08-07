package com.chocolate.local.dao.organization

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.dto.local.users.OrganizationsLocalDto

@Dao
interface OrganizationsDao {

    @Upsert
    suspend fun upsertNameOrg(nameOrg: OrganizationsLocalDto)

    @Query("SELECT * FROM TABLE_ORGANIZATIONS")
    suspend fun getNameOrganizations(): List<OrganizationsLocalDto>

    @Query("DELETE FROM TABLE_ORGANIZATIONS")
    suspend fun deleteOrganizations(nameOrganizations: OrganizationsLocalDto)

}