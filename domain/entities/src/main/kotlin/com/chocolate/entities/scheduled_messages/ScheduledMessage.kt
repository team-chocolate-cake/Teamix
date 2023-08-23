package com.chocolate.entities.scheduled_messages

import java.util.Date

data class ScheduledMessage(
    val id:Int,
    val content: String,
    val deliveryTimestamp: Date,
    val topic: String,
)