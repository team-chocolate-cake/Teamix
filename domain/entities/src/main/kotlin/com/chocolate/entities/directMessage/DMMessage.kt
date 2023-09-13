package com.chocolate.entities.directMessage

import java.util.Date

data class DMMessage(
    val sentBy:String,
    val sentAt:Date,
    val messageText:String,
    val senderFullName: String,
    val senderAvatarUrl: String,
)
