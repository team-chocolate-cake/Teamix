package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class DefaultMessageDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?
)