package com.chocolate.viewmodel.savedlater

import com.chocolate.entities.entity.SavedLaterMessage
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
    val id: String = "",
    val username: String = "",
    val imageUrl: String = "",
    val messageContent: String = "",
    val time: String = ""
)

fun SavedLaterMessage.toMessageUiState(): MessageItemUiState {
    return MessageItemUiState(
        id = id,
        username = sender.name,
        imageUrl = sender.imageUrl,
        messageContent = messageContent,
        time = formatDate(date),
    )
}

fun List<SavedLaterMessage>.toMessagesUiState(): List<MessageItemUiState> {
    return this.map { it.toMessageUiState() }
}

private fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
    return dateFormat.format(date)
}

