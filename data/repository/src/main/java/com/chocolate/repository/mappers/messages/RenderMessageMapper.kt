package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.RenderMessage
import com.chocolate.repository.model.dto.message.response.RenderMessageDto

fun RenderMessageDto.toRenderMessage(): RenderMessage {
    return RenderMessage(
        rendered = this.rendered ?: ""
    )
}