package com.chocolate.viewmodel.saveLater

import com.chocolate.entities.messages.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SaveLaterMessageUiState(
    val messages: List<MessageItemUiState> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val deleteStateMessage: String? = null,
)

data class MessageItemUiState(
    val id: Int = 0,
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)

fun Message.toMessageUiState(): MessageItemUiState {
    return MessageItemUiState(
        id = id,
        username = senderFullName,
        imageUrl = senderAvatarUrl,
        messageContent = messageContent,
        time = formatDate(timestamp),
    )
}

private fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
    return dateFormat.format(date)
}

