package com.chocolate.entities

import java.util.Date

data class Topic(
    val topicId: String,
    val content: String,
    val senderName: String,
    val senderImage: String,
    val sentTIme: Date
)