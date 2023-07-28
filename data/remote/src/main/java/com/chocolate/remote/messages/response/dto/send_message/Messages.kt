package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class Messages(
    @SerializedName("message_id")
    val messageId: MessageId?
)