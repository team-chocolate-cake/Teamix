package com.chocolate.entities.mappers.scheduled

import com.chocolate.entities.scheduled_messages.ScheduledMessages

fun ScheduledMessagesDto.toEntity(): ScheduledMessages {
    return ScheduledMessages(
        uri = this.uri ?: ""
    )
}