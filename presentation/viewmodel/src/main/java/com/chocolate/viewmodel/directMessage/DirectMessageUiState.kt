package com.chocolate.viewmodel.directMessage

import com.chocolate.entities.directMessage.ChatEntity
import com.chocolate.viewmodel.DMChat.formatDate

data class DirectMessageUiState(
    val searchInput: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)

data class ChatUiState(
    val id : String,
    val image: String = "",
    val name: String = "",
    val lastMessage: String = "",
    val lastMessageDate: String = "",
)

fun ChatEntity.toUiState() = ChatUiState(
    id = id,
    name = name,
    image = image,
    lastMessageDate = lastMessageDate.formatDate(),
    lastMessage = lastMessage
)
