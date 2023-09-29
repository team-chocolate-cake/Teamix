package com.chocolate.viewmodel.directmessage

import com.chocolate.entities.entity.Chat
import com.chocolate.viewmodel.directmessagechat.formatDate

fun Chat.toUiState() = ChatUiState(
    id = id,
    name = name,
    image = image,
    lastMessageDate = lastMessageDate.formatDate(),
    lastMessage = lastMessage
)

fun List<Chat>.toUiState() : List<ChatUiState> = this.map { it.toUiState() }
