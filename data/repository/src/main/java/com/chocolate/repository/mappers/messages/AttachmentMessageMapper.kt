package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.repository.dto.remote.message.response.FileRemoteDto

fun FileRemoteDto.toAttachmentMessage(): AttachmentMessage {
    return AttachmentMessage(
        uri = this.uri ?: ""
    )
}