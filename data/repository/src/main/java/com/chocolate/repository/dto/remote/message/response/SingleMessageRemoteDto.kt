package com.chocolate.repository.dto.remote.message.response


import com.google.gson.annotations.SerializedName

data class SingleMessageRemoteDto(
    @SerializedName("message")
    val message: com.chocolate.repository.dto.remote.message.response.Message?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("raw_content")
    val rawContent: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?
)