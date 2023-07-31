package com.chocolate.local.entities.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_table")
data class ChatEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
