package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class SendMessageRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("stream")
    val stream: String?

)