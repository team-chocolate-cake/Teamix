package com.chocolate.entities.messages

data class Message(
    val id: Int,
    val senderAvatarUrl: String,
    val senderId: Int,
    val senderFullName: String,
    val messageContent: String,
    val topicId: Int,
    // val timestamp: Date,
)