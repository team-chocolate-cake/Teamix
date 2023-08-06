package com.chocolate.repository.dto.message.response

import com.google.gson.annotations.SerializedName

data class Messages(
    @SerializedName("message_id")
    val messageId: MessageId?
)