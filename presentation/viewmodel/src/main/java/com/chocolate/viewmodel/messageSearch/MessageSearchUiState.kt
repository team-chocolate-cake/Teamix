package com.chocolate.viewmodel.messageSearch

import com.chocolate.entities.uills.Empty
import com.chocolate.viewmodel.base.BaseErrorUiState
import com.chocolate.viewmodel.pinnedMessages.MessageItemUiState

data class MessageSearchUiState(
    val searchInput: String = String.Empty,
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

