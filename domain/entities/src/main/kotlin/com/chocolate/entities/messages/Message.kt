package com.chocolate.entities.messages

data class Message(
    val avatarUrl: String,
    val client: String,
    val content: String,
    val contentType: String,
    val displayRecipient: Any,
    val flags: List<String>,
    val id: Int,
    val isMeMessage: Boolean,
    val reactions: List<Any>,
    val recipientId: Int,
    val senderEmail: String,
    val senderFullName: String,
    val senderId: Int,
    val senderRealmStr: String,
    val streamId: Int,
    val subject: String,
    val subMessages: List<Any>,
    val timestamp: Long,
    val topicLinks: List<Any>,
    val type: String
)