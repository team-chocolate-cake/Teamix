package com.chocolate.viewmodel.topicmessages

import android.annotation.SuppressLint
import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.UserRole
import com.chocolate.entities.entity.Message
import com.chocolate.entities.entity.SavedLaterMessage
import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.Empty
import com.chocolate.entities.util.toStringDate
import com.chocolate.viewmodel.home.TopicUiState
import java.util.Date

@JvmName("MessageToMessageUiState")
fun Message.toUiState(): MessageUiState =
    MessageUiState(
        replayDate = timestamp.toStringDate(),
        id = id.toInt(),
        userId=senderId,
        message = messageContent,
        username = senderFullName,
        userImage = senderAvatarUrl
    )

@JvmName("MessagesToMessagesUiState")
fun List<Message>.toUiState(): List<MessageUiState> =
    this.map { it.toUiState() }

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
)

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(topicId, content, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState(): List<TopicUiState> = this.map { it.toUiState() }


