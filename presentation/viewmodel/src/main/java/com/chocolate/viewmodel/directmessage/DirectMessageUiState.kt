package com.chocolate.viewmodel.directmessage

import kotlinx.coroutines.flow.MutableStateFlow

data class DirectMessageUiState(
    val searchInput: MutableStateFlow<String> = MutableStateFlow(""),
    val chats: List<ChatUiState> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = true
)

data class ChatUiState(
    val id: String,
    val image: String = "",
    val name: String = "",
    val lastMessage: String = "",
    val lastMessageDate: String = "",
)