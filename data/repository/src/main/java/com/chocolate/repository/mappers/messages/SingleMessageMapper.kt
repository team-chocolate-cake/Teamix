package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.SingleMessage
import com.chocolate.repository.dto.message.response.SingleMessageDto

fun SingleMessageDto.toEntity(): SingleMessage {
    return SingleMessage(
        message = this.message!!.toMessageEntity(),
        msg = this.msg ?: "",
        rawContent = this.rawContent ?: ""
    )
}