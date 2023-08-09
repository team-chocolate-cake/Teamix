package com.chocolate.repository.mappers.scheduled

import com.chocolate.entities.scheduled_messages.ScheduledMessageContent
import com.chocolate.entities.scheduled_messages.ScheduledMessages
import com.chocolate.repository.model.dto.scheduled_message.response.ScheduledMessagesDto

fun ScheduledMessagesDto.toScheduledMessages(): ScheduledMessages {
    val scheduledMessageContents = scheduledMessages?.map { scheduledMessageContentDto ->
        ScheduledMessageContent(
            content = scheduledMessageContentDto?.content ?: "",
            renderedContent = scheduledMessageContentDto?.renderedContent ?: "",
            scheduledDeliveryTimestamp = scheduledMessageContentDto?.scheduledDeliveryTimestamp ?: 0,
            scheduledMessageId = scheduledMessageContentDto?.scheduledMessageId ?: 0,
            to = scheduledMessageContentDto?.to ?: emptyList(),
            topic = scheduledMessageContentDto?.topic ?: "",
            type = scheduledMessageContentDto?.type ?: ""
        )
    } ?: emptyList()

    return ScheduledMessages(scheduledMessages = scheduledMessageContents)
}