package com.chocolate.viewmodel.direct_messages.specific_dm

import com.chocolate.entities.uills.Empty

data class DirectMessageUiState(
    val friendImage: String = "",
    val friendName: String = "",
    val message: List<ContentMessageUiState> = emptyList(),
    val isSent: Boolean = true,
    val isLoading: Boolean = true,
    val error: String? = null
) {
    data class ContentMessageUiState(
        val username: String = String.Empty,
        val replayDate: String = String.Empty,
        val textMessage: String = String.Empty,
        val imageMessage: String = String.Empty,
        val audioMessage: String = String.Empty,
        val reactionImage: Int = -1,
        val reactionCount: Int = 0,
        val isMyReplay: Boolean = false,
    )
}
