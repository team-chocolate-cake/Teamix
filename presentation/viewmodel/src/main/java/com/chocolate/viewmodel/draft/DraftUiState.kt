package com.chocolate.viewmodel.draft

data class DraftUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val messages: List<DraftItemUiState> = emptyList()
)
data class DraftItemUiState(
    val id: Int = 0,
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)