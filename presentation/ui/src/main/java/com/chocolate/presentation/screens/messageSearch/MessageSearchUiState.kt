package com.chocolate.presentation.screens.messageSearch

import com.chocolate.presentation.base.BaseErrorUiState
import com.chocolate.presentation.screens.pinnedMessages.MessageItemUiState

data class MessageSearchUiState(
    val searchInput: String = "",
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

