package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Reaction
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.message.response.MessageDto
import com.chocolate.repository.model.dto.message.response.ReactionDto
import java.util.Date

fun MessageDto.toEntity(): Message {
    return Message(
        senderAvatarUrl = this.avatarUrl.orEmpty(),
        messageContent = this.content.orEmpty(),
        id = this.id ?: 0,
        reactions = this.reactions.toEntity(),
        senderEmail = this.senderEmail.orEmpty(),
        senderFullName = this.senderFullName.orEmpty(),
        senderId = this.senderId ?: 0,
        streamId = this.streamId ?: 0,
        topic = this.subject.orEmpty(),
        timestamp = this.timestamp?.toDate() ?: Date(),
    )
}

@JvmName("MessageDto")
fun List<MessageDto>?.toEntity(): List<Message> = this?.map { it.toEntity() }.orEmpty()

@JvmName("ReactionDto")
fun List<ReactionDto>?.toEntity(): List<Reaction> =
    this?.map { Reaction(emojiCode = it.emoji_code, emojiName = it.emoji_name) }.orEmpty()