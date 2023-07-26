package com.chocolate.remote.messages.request

import com.google.gson.annotations.SerializedName

data class EditMessageRequest(
    @SerializedName("message_id")
    val message_id: Int,
    @SerializedName("content")
    val content: String = ""

)
