package com.chocolate.viewmodel.topic

import android.annotation.SuppressLint
import com.chocolate.entities.messages.Message
import com.chocolate.entities.topic.Topic
import com.chocolate.entities.uills.toStringDate
import com.chocolate.viewmodel.home.TopicUiState
import java.util.Date

@JvmName("MessageToMessageUiState")
fun Message.toUiState(isMyMessage: Boolean): MessageUiState =
    MessageUiState(
        replayDate = timestamp.toStringDate(),
        id = id.toInt(),
        userId=senderId,
        message = messageContent,
        username = senderFullName,
        isMyReplay = isMyMessage,
        userImage = senderAvatarUrl
    )

@JvmName("MessagesToMessagesUiState")
fun List<Message>.toUiState(isMyMessage: Boolean): List<MessageUiState> =
    this.map { it.toUiState(isMyMessage) }

@SuppressLint("SimpleDateFormat")
@JvmName("MessageUiStateToMessage")
fun MessageUiState.toMessage(): Message {
    return Message(
        id = id.toString(),
        senderId=userId,
        messageContent = message,
        senderFullName = username,
        senderAvatarUrl = userImage,
        timestamp= Date(),
    )
}

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(topicId, content, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState(): List<TopicUiState> = this.map { it.toUiState() }


