package com.chocolate.repository.dto.remote.scheduled_message.response

import com.google.gson.annotations.SerializedName

data class ScheduledMessagesDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("scheduled_messages")
    val scheduledMessages: List<ScheduledMessageContentDto?>?
)