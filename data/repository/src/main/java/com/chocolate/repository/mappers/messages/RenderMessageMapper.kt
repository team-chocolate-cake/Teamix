package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.AttachmentMessage
import com.chocolate.entities.messages.RenderMessage
import com.chocolate.repository.dto.message.response.FileDto
import com.chocolate.repository.dto.message.response.RenderMessageDto

fun RenderMessageDto.toEntity(): RenderMessage {
    return RenderMessage(
        message = this.message ?: "",
        rendered = this.rendered ?: ""
    )
}