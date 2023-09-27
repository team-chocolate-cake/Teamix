package com.chocolate.entities.entity

import java.util.Date

data class Message(
    val id: String,
    val senderAvatarUrl: String,
    val senderId: String,
    val senderFullName: String,
    val messageContent: String,
    val timestamp: Date,
)