package com.chocolate.repository.dto.remote.message.response


import com.google.gson.annotations.SerializedName

data class MatchNarrowRemoteDto(
    @SerializedName("messages")
    val messages: com.chocolate.repository.dto.remote.message.response.Messages?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)