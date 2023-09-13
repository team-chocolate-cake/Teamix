package com.chocolate.repository.model.dto.direct_message

import java.util.Date

data class Chat(
    val id :String = "",
    val secondMember:String = "",
    val lastMessage:String = "",
    val lastMessageDate: Date = Date(),
)
