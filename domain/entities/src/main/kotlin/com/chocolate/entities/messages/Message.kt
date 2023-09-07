package com.chocolate.entities.messages

import java.util.Date

data class Message(
    val id: Int,
    val senderAvatarUrl: String,
    val senderId: Int,
    //val senderEmail: String,
    val senderFullName: String,
   // val reactions: List<Reaction>,
    val messageContent: String,
    val streamId: Int,
   // val topic: String,
   // val timestamp: Date,
)