package com.chocolate.viewmodel.saveLater

data class SaveLaterMessageUiState(
    val messages: Map<String, List<MessageItemUiState>> = emptyMap()
)

data class MessageItemUiState(
    val id: Int = 0,
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)