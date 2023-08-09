package com.chocolate.repository.mappers.scheduled

import com.chocolate.entities.scheduled_messages.BaseScheduledMessage
import com.chocolate.repository.dto.remote.scheduled_message.response.BaseScheduledMessageResponse

fun BaseScheduledMessageResponse.toBaseScheduledMessage(): BaseScheduledMessage {
    return BaseScheduledMessage(
        scheduledMessageId = this.scheduledMessageId ?: 0,
        streamId = this.streamId ?: 0
    )
}