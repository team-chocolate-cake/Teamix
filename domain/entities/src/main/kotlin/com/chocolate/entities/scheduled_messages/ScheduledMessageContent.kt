package com.chocolate.entities.scheduled_messages

data class ScheduledMessageContent(
    val content: String,
    val renderedContent: String,
    val scheduledDeliveryTimestamp: Int,
    val scheduledMessageId: Int,
    val to: List<Int>,
    val topic: String,
    val type: String
)