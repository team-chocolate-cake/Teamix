package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class MessagesDto(
    @SerializedName("message_id")
    val messageId: MessageIdDto?
)