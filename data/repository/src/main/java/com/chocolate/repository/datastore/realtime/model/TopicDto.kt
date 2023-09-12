package com.chocolate.repository.datastore.realtime.model

import com.chocolate.entities.topic.Topic
import java.util.Date

data class TopicDto(
    val topicId: String? = null,
    val content: String? = null,
)