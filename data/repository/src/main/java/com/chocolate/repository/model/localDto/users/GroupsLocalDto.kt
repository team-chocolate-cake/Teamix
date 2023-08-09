package com.chocolate.repository.model.localDto.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupsLocalDto(
    @PrimaryKey
    val id: String,
    val name: String
)
