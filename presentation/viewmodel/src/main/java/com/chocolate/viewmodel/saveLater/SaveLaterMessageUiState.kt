package com.chocolate.viewmodel.saveLater

data class SaveLaterMessageUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class MessageItemUiState(
    val id: Int = 0,
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)