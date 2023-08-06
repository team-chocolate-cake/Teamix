package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Messages
import com.chocolate.repository.dto.message.response.Message
import com.chocolate.repository.dto.message.response.MessagesDto


fun Message.toMessageEntity(): com.chocolate.entities.messages.Message {
    return com.chocolate.entities.messages.Message(
        avatarUrl = this.avatarUrl ?: "",
        client = this.client ?: "",
        content = this.content ?: "",
        contentType = this.contentType ?: "",
        displayRecipient = this.displayRecipient ?: "",
        flags = this.flags ?: emptyList(),
        id= this.id ?: 0,
        isMeMessage = this.isMeMessage ?: false,
        reactions = this.reactions ?: emptyList(),
        recipientId = this.recipientId ?: 0,
        senderEmail = this.senderEmail ?: "",
        senderFullName = this.senderFullName ?: "",
        senderId = this.senderId ?: 0,
        senderRealmStr = this.senderRealmStr ?: "",
        streamId = this.streamId ?: 0,
        subject = this.subject ?: "",
        subMessages = this.subMessages ?: emptyList(),
        timestamp = this.timestamp ?: 0L,
        topicLinks = this.topicLinks ?: emptyList(),
        type = this.type ?: ""
    )
}
fun MessagesDto.toEntity(): Messages{
    return Messages(
        messages = this.messages?.map { it.toMessageEntity() } ?: emptyList()
    )
}