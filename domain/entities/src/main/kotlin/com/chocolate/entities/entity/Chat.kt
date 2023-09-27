package com.chocolate.entities.entity

import java.util.Date

data class Chat(
    val id: String,
    val name: String,
    val image:String,
    val lastMessage:String,
    val lastMessageDate:Date
)