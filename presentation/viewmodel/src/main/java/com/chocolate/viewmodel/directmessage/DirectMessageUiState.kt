package com.chocolate.viewmodel.directmessage

import com.chocolate.entities.Chat
import com.chocolate.viewmodel.directmessagechat.formatDate
import kotlinx.coroutines.flow.MutableStateFlow

data class DirectMessageUiState(
    val searchInput: MutableStateFlow<String> = MutableStateFlow(""),
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

fun Chat.toUiState() = ChatUiState(
    id = id,
    name = name,
    image = image,
    lastMessageDate = lastMessageDate.formatDate(),
    lastMessage = lastMessage
)

fun List<Chat>.toUiState() : List<ChatUiState> = this.map { it.toUiState() }
