package com.chocolate.repository.model.localDto.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_CURRENT_USER")
data class UserLocalDto(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val username: String,
    val email: String,
    val role: Int,
    val imageUrl: String
)

