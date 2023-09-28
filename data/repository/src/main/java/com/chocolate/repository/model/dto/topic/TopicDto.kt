package com.chocolate.repository.model.dto.topic

import java.util.Date

data class TopicDto(
    val topicId: String? = null,
    val name: String? = null,
    val senderName: String? = null,
    val senderImage: String? = null,
    val sentTIme: Date? = null
)