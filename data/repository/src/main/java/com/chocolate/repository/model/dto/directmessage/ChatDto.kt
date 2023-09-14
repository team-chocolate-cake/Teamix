package com.chocolate.repository.model.dto.directmessage

import java.util.Date

data class ChatDto(
    val id :String = "",
    val secondMemberId:String = "",
    val lastMessage:String = "",
    val lastMessageDate: Date = Date(),
)
