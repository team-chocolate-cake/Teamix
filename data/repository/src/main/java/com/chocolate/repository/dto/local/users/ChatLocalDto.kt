package com.chocolate.repository.dto.local.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_table")
data class ChatLocalDto(
    @PrimaryKey
    val id: String,
    val name: String
)
