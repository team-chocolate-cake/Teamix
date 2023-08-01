package com.chocolate.repository.dto.remote.message.response


import com.google.gson.annotations.SerializedName

data class RenderMessageRemoteDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("rendered")
    val rendered: String?,
    @SerializedName("result")
    val result: String?
)