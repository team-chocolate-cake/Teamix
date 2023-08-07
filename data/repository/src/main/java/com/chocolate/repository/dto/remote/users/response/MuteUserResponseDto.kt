package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class MuteUserResponseDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)