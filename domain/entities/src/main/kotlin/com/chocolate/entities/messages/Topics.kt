package com.chocolate.entities.messages

import com.chocolate.entities.user.Member

data class Topic(
    val content: String,
    val messages: List<Message>,
    val topicSender: Member
)
