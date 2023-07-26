package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class RenderMessageRemoteDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("rendered")
    val rendered: String?,
    @SerializedName("result")
    val result: String?
)