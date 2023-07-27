package com.chocolate.remote.scheduled_message.dto


import com.google.gson.annotations.SerializedName

data class ScheduledMessagesDto(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("scheduled_messages")
    val scheduledMessages: List<ScheduledMessageDto>
)