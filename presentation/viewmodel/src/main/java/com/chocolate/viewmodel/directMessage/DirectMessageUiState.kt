package com.chocolate.viewmodel.directMessage

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
