package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class MessageReadReceiptsRemoteDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_ids")
    val userIds: List<Int?>?
)