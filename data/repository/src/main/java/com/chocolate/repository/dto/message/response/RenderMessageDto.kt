package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class RenderMessageDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("rendered")
    val rendered: String?,
    @SerializedName("result")
    val result: String?
)