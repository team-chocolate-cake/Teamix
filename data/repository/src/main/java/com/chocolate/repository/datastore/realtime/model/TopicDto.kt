package com.chocolate.repository.datastore.realtime.model

import com.chocolate.entities.messages.Topic
import java.util.Date

data class TopicDto(
    val topicId: String? = null,
    val content: String? = null,
    val senderName: String? = null,
    val senderId: String? = null,
    val senderImageUrl: String? = null,
    val sentTime: Date? = null
)
fun TopicDto.toEntity(): Topic {
    return Topic(
        topicId = topicId!!,
        content = content ?: "",
        senderName = senderName ?: "",
        senderId = senderId ?: "",
        senderImageUrl = senderImageUrl ?: "",
        sentTime = sentTime ?: Date(),
    )
}