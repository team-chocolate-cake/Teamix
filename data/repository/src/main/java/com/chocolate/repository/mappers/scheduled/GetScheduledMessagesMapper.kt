package com.chocolate.repository.mappers.scheduled

import com.chocolate.entities.scheduled_messages.ScheduledMessage
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.scheduled_message.response.ScheduledMessageContentDto
import java.util.Date

fun ScheduledMessageContentDto.toEntity(): ScheduledMessage {
    return ScheduledMessage(
        content = this.content.orEmpty(),
        deliveryTimestamp = this.scheduledDeliveryTimestamp?.toDate() ?: Date(),
        id = this.scheduledMessageId ?: 0,
        topic = this.topic.orEmpty(),
    )
}

fun List<ScheduledMessageContentDto>?.toEntity(): List<ScheduledMessage> =
    this?.map { it.toEntity() }.orEmpty()