package com.chocolate.repository.model.localDto.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_ORGANIZATIONS")
data class OrganizationsLocalDto(
    @PrimaryKey(autoGenerate = false)
    val organizationName: String
)
