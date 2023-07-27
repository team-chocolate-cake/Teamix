package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class FileRemoteDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("uri")
    val uri: String?
)