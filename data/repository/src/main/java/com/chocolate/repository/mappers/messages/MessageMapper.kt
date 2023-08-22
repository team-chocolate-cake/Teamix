package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Reaction
import com.chocolate.repository.model.dto.message.response.MessageDto
import com.chocolate.repository.model.dto.message.response.ReactionDto


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
        timestamp = this.timestamp ?: 0L,
    )
}

fun List<MessageDto>?.toEntity(): List<Message> = this?.map { it.toEntity() }.orEmpty()
fun List<ReactionDto>?.toEntity(): List<Reaction> =
    this?.map { Reaction(emojiCode = it.emoji_code, emojiName = it.emoji_name) }.orEmpty()