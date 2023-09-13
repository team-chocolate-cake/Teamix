package com.chocolate.repository.datastore.realtime.model

import com.chocolate.entities.messages.Message
import java.util.Date


data class MessageDto(
    val id: String? = null,
    val content: String? = null,
    val userId: String? = null,
    val senderName: String? = null,
    val senderImage: String? = null,
    val timestamp: Date? = null,
)
