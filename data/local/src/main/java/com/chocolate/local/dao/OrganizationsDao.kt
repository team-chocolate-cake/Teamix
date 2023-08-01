package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.users.OrganizationsEntity

@Dao
interface OrganizationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNameOrg(nameOrg: String)

    @Query("SELECT * FROM TABLE_ORGANIZATIONS WHERE nameOrganizations = :nameOrganizations")
    suspend fun getNameOrganizations(nameOrganizations: String): List<OrganizationsEntity>

    @Delete
    suspend fun deleteOrganizations(nameOrganizations: String)
}