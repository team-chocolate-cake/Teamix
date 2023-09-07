package com.chocolate.repository.datastore.realtime.model

import com.chocolate.entities.user.Member
import java.sql.Timestamp
import java.util.Date

data class TopicDto(
    val senderId: String? = null,
    val content: String? = null,
    val sentTime: Date? = null
)
