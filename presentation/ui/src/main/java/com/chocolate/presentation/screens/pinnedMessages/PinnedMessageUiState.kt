package com.chocolate.presentation.screens.pinnedMessages

import com.chocolate.presentation.base.BaseErrorUiState

data class PinnedMessagesUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class MessageItemUiState(
    val imageUrl: String = "",
    val name: String = "",
    val messageContent: String = "",
    val date: String = ""
)

