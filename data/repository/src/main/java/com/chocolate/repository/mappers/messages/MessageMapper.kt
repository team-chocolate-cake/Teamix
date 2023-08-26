package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Reaction
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.message.response.MessageDto
import com.chocolate.repository.model.dto.message.response.ReactionDto
import com.chocolate.repository.model.localDto.message.SavedMessageLocalDto
import java.util.Date


fun MessageDto.toEntity(): Message {
    return Message(
        senderAvatarUrl = this.avatarUrl ?: "",
        messageContent = this.content ?: "",
        id = this.id ?: 0,
        reactions = this.reactions.toEntity(),
        senderEmail = this.senderEmail ?: "",
        senderFullName = this.senderFullName ?: "",
        senderId = this.senderId ?: 0,
        streamId = this.streamId ?: 0,
        topic = this.subject ?: "",
        timestamp = this.timestamp?.toDate() ?: Date(),
    )
}

@JvmName("MessageDto")
fun List<MessageDto>?.toEntity(): List<Message> = this?.map { it.toEntity() }.orEmpty()

@JvmName("ReactionDto")
fun List<ReactionDto>?.toEntity(): List<Reaction> =
    this?.map { Reaction(emojiCode = it.emoji_code, emojiName = it.emoji_name) }.orEmpty()

fun SavedMessageLocalDto.toEntity(): Message{
    return Message(
        id = this.id,
        senderAvatarUrl = this.senderImageUrl,
        senderId = this.senderId,
        senderEmail = "",
        senderFullName = this.senderName,
        reactions = emptyList(),
        messageContent = this.messageContent,
        streamId = 0,
        topic = "",
        timestamp = this.date

    )
}

fun Message.toLocalDto(): SavedMessageLocalDto{
    return SavedMessageLocalDto(
        id = this.id,
        senderId = this.senderId,
        senderName = this.senderFullName,
        senderImageUrl = this.senderAvatarUrl,
        messageContent = this.messageContent,
        date = this.timestamp

    )
}