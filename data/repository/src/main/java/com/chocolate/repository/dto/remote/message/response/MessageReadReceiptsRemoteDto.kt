package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class MessageReadReceiptsRemoteDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_ids")
    val userIds: List<Int?>?
)