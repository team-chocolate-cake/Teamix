package com.chocolate.viewmodel.topic

import com.chocolate.entities.messages.Message

fun Message.toUiState(isMyMessage:Boolean): MessageUiState =
    MessageUiState(
        id=id,
        message=messageContent,
        username=senderFullName,
        isMyReplay = isMyMessage,
        userImage = senderAvatarUrl
    )

@JvmName("MessageToMessageUiState")
fun List<Message>.toUiState(isMyMessage:Boolean): List<MessageUiState> = this.map { it.toUiState(isMyMessage) }

