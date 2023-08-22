package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Reaction
import com.chocolate.repository.model.dto.message.response.MessageDto
import com.chocolate.repository.model.dto.message.response.ReactionDto


fun MessageDto.toMessage(): Message {
    return Message(
        senderAvatarUrl = this.avatarUrl ?: "",
        messageContent = this.content ?: "",
        messageContentType = this.contentType ?: "",
        id = this.id ?: 0,
        reactions = this.reactions.toReactions(),
        senderEmail = this.senderEmail ?: "",
        senderFullName = this.senderFullName ?: "",
        senderId = this.senderId ?: 0,
        streamId = this.streamId ?: 0,
        topic = this.subject ?: "",
        timestamp = this.timestamp ?: 0L,
    )
}

fun List<MessageDto>?.toMessages(): List<Message> = this?.map { it.toMessage() } ?: emptyList()
fun List<ReactionDto>?.toReactions(): List<Reaction> =
    this?.map { Reaction(emojiCode = it.emoji_code, emojiName = it.emoji_name) } ?: emptyList()