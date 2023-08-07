package com.chocolate.presentation.screens.pinnedMessages

data class PinnedMessagesUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: ErrorUiState = ErrorUiState()
)

data class MessageItemUiState(
    val imageUrl: String = "",
    val name: String = "",
    val messageContent: String = "",
    val date: String = ""
)

data class ErrorUiState(
    val message: String = "",
    val isError: Boolean = false
)
