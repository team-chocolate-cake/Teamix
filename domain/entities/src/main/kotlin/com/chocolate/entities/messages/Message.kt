package com.chocolate.entities.messages

data class Message(
    val id: Int,
    val senderAvatarUrl: String,
    val senderId: Int,
    val senderEmail: String,
    val senderFullName: String,
    val reactions: List<Reaction>,
    val messageContent: String,
    val messageContentType: String,
    val streamId: Int,
    val topic: String,
    val timestamp: Long,
)