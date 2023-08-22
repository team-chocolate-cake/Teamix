package com.chocolate.repository.mappers.scheduled

import com.chocolate.entities.scheduled_messages.ScheduledMessage
import com.chocolate.repository.model.dto.scheduled_message.response.ScheduledMessageContentDto

fun ScheduledMessageContentDto.toEntity(): ScheduledMessage {
    return ScheduledMessage(
        content = this.content ?: "",
        deliveryTimestamp = this.scheduledDeliveryTimestamp ?: 0,
        id = this.scheduledMessageId ?: 0,
        topic = this.topic ?: "",
    )
}

fun List<ScheduledMessageContentDto>?.toEntity(): List<ScheduledMessage> =
    this?.map { it.toEntity() }.orEmpty()
