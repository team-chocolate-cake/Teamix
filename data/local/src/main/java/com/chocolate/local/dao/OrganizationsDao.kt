package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.users.OrganizationsLocalDto

@Dao
interface OrganizationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNameOrg(nameOrg: OrganizationsLocalDto)

    @Query("SELECT * FROM TABLE_ORGANIZATIONS")
    suspend fun getNameOrganizations(): List<OrganizationsLocalDto>

    @Delete
    suspend fun deleteOrganizations(nameOrganizations: OrganizationsLocalDto)
}