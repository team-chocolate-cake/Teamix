package com.chocolate.entities.directMessage

import java.util.Date

data class DirectMessage(
    val sentBy:String,
    val sentAt:Date,
    val messageContent:String,
    val senderFullName: String,
    val senderImageUrl: String,
)
