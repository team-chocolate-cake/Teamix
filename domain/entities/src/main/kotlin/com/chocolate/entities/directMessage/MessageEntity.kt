package com.chocolate.entities.directMessage

import java.util.Date

data class MessageEntity(
    val sentBy:String,
    val sentAt:Date,
    val messageContent:String,
    val senderFullName: String,
    val senderImageUrl: String,
)
