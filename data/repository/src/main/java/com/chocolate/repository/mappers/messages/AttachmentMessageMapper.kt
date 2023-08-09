package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.repository.model.dto.message.response.FileRemoteDto

fun FileRemoteDto.toAttachmentMessage(): AttachmentMessage {
    return AttachmentMessage(
        uri = this.uri ?: ""
    )
}