package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.repository.dto.message.response.FileDto

fun FileDto.toEntity(): AttachmentMessage {
    return AttachmentMessage(
        message = this.message ?: "",
        uri = this.uri ?: ""
    )
}