package com.chocolate.repository.dto.remote.scheduled_message.response


import com.google.gson.annotations.SerializedName

data class ScheduledMessageContentDto(
    @SerializedName("content")
    val content: String?,
    @SerializedName("failed")
    val failed: Boolean?,
    @SerializedName("rendered_content")
    val renderedContent: String?,
    @SerializedName("scheduled_delivery_timestamp")
    val scheduledDeliveryTimestamp: Int?,
    @SerializedName("scheduled_message_id")
    val scheduledMessageId: Int?,
    @SerializedName("to")
    val to: List<Int>?,
    @SerializedName("topic")
    val topic: String?,
    @SerializedName("type")
    val type: String?
)