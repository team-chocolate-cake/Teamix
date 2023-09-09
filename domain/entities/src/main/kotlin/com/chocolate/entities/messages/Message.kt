package com.chocolate.entities.messages

import java.util.Date

data class Message(
    val id: Int,
    val senderAvatarUrl: String,
    val senderId: Int,
    val senderFullName: String,
    val messageContent: String,
    val streamId: Int,
    // val timestamp: Date,

)