package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.SingleMessage
import com.chocolate.repository.dto.remote.message.response.SingleMessageRemoteDto

fun SingleMessageRemoteDto.toEntity(): SingleMessage {
    return SingleMessage(
        message = this.message!!.toMessageEntity(),
        rawContent = this.rawContent ?: ""
    )
}