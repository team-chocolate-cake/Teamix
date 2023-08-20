package com.chocolate.repository.model.dto.users.response


import com.google.gson.annotations.SerializedName

data class StatusUserRemoteDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_status")
    val userStatus: Map<String,UserStatus>?
)