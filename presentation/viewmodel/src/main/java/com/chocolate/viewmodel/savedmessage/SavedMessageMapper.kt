package com.chocolate.viewmodel.savedmessage

import com.chocolate.entities.entity.SavedMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun SavedMessage.toMessageUiState(): MessageItemUiState {
    return MessageItemUiState(
        id = id,
        username = sender.name,
        imageUrl = sender.imageUrl,
        messageContent = messageContent,
        time = formatDate(date),
    )
}

fun List<SavedMessage>.toMessagesUiState(): List<MessageItemUiState> {
    return this.map { it.toMessageUiState() }
}

private fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
    return dateFormat.format(date)
}

