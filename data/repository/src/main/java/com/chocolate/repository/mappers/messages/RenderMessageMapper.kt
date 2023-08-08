package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.RenderMessage
import com.chocolate.repository.dto.remote.message.response.RenderMessageDto

fun RenderMessageDto.toRenderMessage(): RenderMessage {
    return RenderMessage(
        rendered = this.rendered ?: ""
    )
}