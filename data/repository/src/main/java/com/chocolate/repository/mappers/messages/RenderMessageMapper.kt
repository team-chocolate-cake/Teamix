package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.RenderMessage
import com.chocolate.repository.dto.remote.message.response.RenderMessageRemoteDto

fun RenderMessageRemoteDto.toEntity(): RenderMessage {
    return RenderMessage(
        rendered = this.rendered ?: ""
    )
}