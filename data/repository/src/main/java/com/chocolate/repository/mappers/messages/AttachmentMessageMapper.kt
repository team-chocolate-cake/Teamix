package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.repository.dto.remote.message.response.FileRemoteDto

fun FileRemoteDto.toEntity(): AttachmentMessage {
    return AttachmentMessage(
        uri = this.uri ?: ""
    )
}