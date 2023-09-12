package com.chocolate.viewmodel.directMessage

import com.chocolate.entities.directMessage.DMChat

data class DirectMessageUiState(
    val searchInput: String = "",
    val chats: List<ChatUiState> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)

data class ChatUiState(
    val image: String = "",
    val name: String = "",
    val lastMessage: String = "",
    val lastMessageDate: String = "",
)

fun DMChat.toUiState() = ChatUiState(
    name = name,
    image = image,
    lastMessageDate = lastMessageDate.time.toString(),
    lastMessage = lastMessage
)
