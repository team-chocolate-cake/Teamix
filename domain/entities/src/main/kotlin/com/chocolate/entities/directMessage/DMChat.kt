package com.chocolate.entities.directMessage

import java.util.Date

data class DMChat(
    val id: String,
    val name: String,
    val image:String,
    val lastMessage:String,
    val lastMessageDate:Date
)
