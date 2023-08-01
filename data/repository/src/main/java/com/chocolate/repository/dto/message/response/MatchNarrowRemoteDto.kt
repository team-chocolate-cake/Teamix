package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class MatchNarrowRemoteDto(
    @SerializedName("messages")
    val messages: Messages?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)