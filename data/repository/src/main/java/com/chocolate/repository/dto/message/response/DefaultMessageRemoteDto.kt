package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class DefaultMessageRemoteDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?
)