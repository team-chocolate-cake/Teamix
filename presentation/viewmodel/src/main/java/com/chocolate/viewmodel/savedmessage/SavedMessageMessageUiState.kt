package com.chocolate.viewmodel.savedmessage

data class SaveMessageUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val deleteStateMessage: String? = null,
)

data class MessageItemUiState(
    val id: String = "",
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)