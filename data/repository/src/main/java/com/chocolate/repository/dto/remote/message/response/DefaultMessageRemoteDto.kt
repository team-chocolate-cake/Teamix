package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class DefaultMessageRemoteDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("code")
    val code: String?
)