package com.chocolate.local.entities.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_ORGANIZATIONS")
data class OrganizationsEntity(
    @PrimaryKey
    val nameOrganizations: String
)
