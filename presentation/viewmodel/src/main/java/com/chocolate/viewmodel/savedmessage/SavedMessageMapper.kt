package com.chocolate.viewmodel.savedmessage

import com.chocolate.entities.entity.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Message.toSavedMessageUiState(): MessageItemUiState {
    return MessageItemUiState(
        id = id,
        username = senderFullName,
        imageUrl = senderAvatarUrl,
        messageContent = messageContent,
        time = formatDate(timestamp),
    )
}

fun List<Message>.toSavedMessagesUiState(): List<MessageItemUiState> {
    return this.map { it.toSavedMessageUiState() }
}

private fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("MMM dd", Locale.US)
    return dateFormat.format(date)
}

