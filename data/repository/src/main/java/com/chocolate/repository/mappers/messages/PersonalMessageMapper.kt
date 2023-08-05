package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessageEntity
import com.chocolate.repository.dto.message.response.PersonalMessageFlagsRemoteDto

fun PersonalMessageFlagsRemoteDto.toEntity(): PersonalMessageEntity {
    return PersonalMessageEntity(
        messages = this.messages ?: emptyList()
    )
}