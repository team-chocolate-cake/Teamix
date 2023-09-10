package com.chocolate.entities.messages


import java.util.Date

data class Topic(
    val topicId: String,
    val content: String,
    val senderName: String,
    val senderId: String,
    val senderImageUrl: String,
    val sentTime: Date,
)
