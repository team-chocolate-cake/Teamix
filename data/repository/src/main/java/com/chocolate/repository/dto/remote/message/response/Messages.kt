package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class Messages(
    @SerializedName("message_id")
    val messageId: MessageId?
)