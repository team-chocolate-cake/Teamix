package com.chocolate.repository.datastore.realtime.model

import java.util.Date

data class TopicDto(
    val content: String? = null,
    val messages: List<MessageDto>? = null,
    val senderName: String? = null,
    val senderId: String? = null,
    val senderImageUrl: String? = null,
    val sentTime: Date? = null
)
