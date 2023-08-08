package com.chocolate.repository.dto.local.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupsEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
