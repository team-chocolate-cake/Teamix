package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.SendMessageEntity
import com.chocolate.repository.dto.message.response.SendMessageRemoteDto

fun SendMessageRemoteDto.toEntity(): SendMessageEntity{
    return SendMessageEntity(
        id = this.id ?: 0
    )
}