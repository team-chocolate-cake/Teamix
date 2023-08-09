package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class MatchNarrowDto(
    @SerializedName("messages")
    val messages: MessagesDto?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)