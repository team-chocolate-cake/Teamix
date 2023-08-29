package com.chocolate.viewmodel.conversation

import com.chocolate.entities.uills.Empty

data class ConversationUiState(
    val messages: List<MessageUiState> = emptyList(),
    val username: String = String.Empty,
    val messageInput: String = String.Empty,
    val isLoading:Boolean = false,
    val error : String? = null
)

data class MessageUiState(
    val username: String = String.Empty,
    val replayDate: String = String.Empty,
    val userImage: String = String.Empty,
    val messageImageUrl: String = String.Empty,
    val isMyReplay: Boolean = false,
    val friendId: Int = 0,
    val message: String = String.Empty,
)
