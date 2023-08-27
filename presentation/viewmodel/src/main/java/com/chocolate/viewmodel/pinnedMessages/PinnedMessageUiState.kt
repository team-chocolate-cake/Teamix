package com.chocolate.viewmodel.pinnedMessages

import com.chocolate.entities.uills.Empty
import com.chocolate.viewmodel.base.BaseErrorUiState

data class PinnedMessagesUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class MessageItemUiState(
    val imageUrl: String = String.Empty,
    val name: String = String.Empty,
    val messageContent: String = String.Empty,
    val date: String = String.Empty
)

