package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class SingleMessageRemoteDto(
    @SerializedName("message")
    val message: Message?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("raw_content")
    val rawContent: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?
)