package com.chocolate.viewmodel.topicmessages

import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.toStringDate
import com.chocolate.viewmodel.home.TopicUiState
import java.util.Date

@JvmName("MessageToMessageUiState")
fun Message.toUiState(): MessageUiState =
    MessageUiState(
        replayDate = timestamp.toStringDate(),
        id = id.toInt(),
        userId = senderId,
        message = messageContent,
        username = senderFullName,
        userImage = senderAvatarUrl
    )

@JvmName("MessagesToMessagesUiState")
fun List<Message>.toUiState(): List<MessageUiState> =
    this.map { it.toUiState() }

@JvmName("MessageUiStateToSavedLaterMessage")
fun MessageUiState.toEntity(): Message {
    return Message(
        id = id.toString(),
        senderId = userId,
        senderFullName=username,
        senderAvatarUrl = userImage,
        messageContent = message,
        timestamp = Date(),
    )
}

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(topicId, name, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState(): List<TopicUiState> = this.map { it.toUiState() }


