package com.chocolate.repository.model.localDto.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "saved_message_table")
data class SavedMessageLocalDto(
    @PrimaryKey
    val id: Int,
    val senderId: Int,
    val senderName: String,
    val senderImageUrl: String,
    val messageContent: String,
    val date: Date
)
