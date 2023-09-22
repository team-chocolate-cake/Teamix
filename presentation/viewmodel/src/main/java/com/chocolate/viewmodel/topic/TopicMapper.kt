package com.chocolate.viewmodel.topic

import android.annotation.SuppressLint
import com.chocolate.entities.member.Member
import com.chocolate.entities.member.UserRole
import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.SavedLaterMessage
import com.chocolate.entities.topic.Topic
import com.chocolate.entities.uills.Empty
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

@JvmName("MessageUiStateToSavedLaterMessage")
fun MessageUiState.toEntity(): SavedLaterMessage {
    return SavedLaterMessage(
        id = id.toString(),
        sender = getFakeMember(userId),
        messageContent = message,
        date = Date(),
    )
}

private fun getFakeMember(id: String) = Member(
    id = id,
    name = String.Empty,
    email = String.Empty,
    password = String.Empty,
    imageUrl = String.Empty,
    isActive = true,
    role = UserRole.MEMBER,
    status = String.Empty,
    channelsId = emptyList()
)

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(topicId, content, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState(): List<TopicUiState> = this.map { it.toUiState() }


