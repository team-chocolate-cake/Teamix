package com.chocolate.remote.messages.request

import com.google.gson.annotations.SerializedName

data class SendMessageRequest(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("to")
    val to: String = "",
    @SerializedName("topic")
    val topic: String = "",
    @SerializedName("content")
    val content: String = ""
)
