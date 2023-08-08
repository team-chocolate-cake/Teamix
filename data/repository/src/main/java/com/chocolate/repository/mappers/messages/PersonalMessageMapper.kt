package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.PersonalMessage
import com.chocolate.repository.dto.remote.message.response.PersonalMessageFlags

fun PersonalMessageFlags.toEntity(): PersonalMessage {
    return PersonalMessage(
        messages = this.messages ?: emptyList()
    )
}