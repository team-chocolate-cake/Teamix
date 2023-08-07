package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class UserStateDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("presence")
    val presence: Presence?,
    @SerializedName("result")
    val result: String?
)