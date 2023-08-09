package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.MessageReadReceipts
import com.chocolate.repository.model.dto.message.response.MessageReadReceiptsDto

fun MessageReadReceiptsDto.toMessageReadReceipts(): MessageReadReceipts {
    return MessageReadReceipts(
        usersID = this.userIds ?: emptyList()
    )
}