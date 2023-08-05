package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessage
import com.chocolate.repository.dto.message.response.PersonalMessageFlagsDto

fun PersonalMessageFlagsDto.toEntity(): PersonalMessage {
    return PersonalMessage(
        messages = this.messages ?: emptyList()
    )
}