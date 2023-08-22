package com.chocolate.entities.scheduled_messages

data class ScheduledMessage(
    val id:Int,
    val content: String,
    val deliveryTimestamp: Int,
    val topic: String,
)