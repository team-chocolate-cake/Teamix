package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class MessageEditHistoryDto(
    @SerializedName("message_history")
    val messageHistory: List<MessageHistoryDto>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String
)