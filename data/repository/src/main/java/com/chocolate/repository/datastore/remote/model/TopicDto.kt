package com.chocolate.repository.datastore.remote.model

import java.util.Date

data class TopicDto(
    val topicId: String? = null,
    val content: String? = null,
    val senderName: String? = null,
    val senderImage: String? = null,
    val sentTIme: Date? = null
)