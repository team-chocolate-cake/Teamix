package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessage
import com.chocolate.repository.dto.remote.message.response.PersonalMessageFlagsDto

fun PersonalMessageFlagsDto.toPersonalMessage(): PersonalMessage {
    return PersonalMessage(
        messages = this.messages ?: emptyList()
    )
}