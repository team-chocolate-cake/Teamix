package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.SendMessage
import com.chocolate.repository.dto.message.response.SendMessageDto

fun SendMessageDto.toEntity(): SendMessage{
    return SendMessage(
        id = this.id ?: 0
    )
}