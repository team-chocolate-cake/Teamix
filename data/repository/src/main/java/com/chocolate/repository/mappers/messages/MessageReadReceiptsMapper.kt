package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.MessageReadReceipts
import com.chocolate.repository.dto.message.response.MessageReadReceiptsDto

fun MessageReadReceiptsDto.toEntity(): MessageReadReceipts {
    return MessageReadReceipts(
        usersID = this.userIds ?: emptyList()
    )
}