package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class UsersStateDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("presences")
    val presences: com.chocolate.repository.dto.remote.users.response.Presences?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("server_timestamp")
    val serverTimestamp: Double?
)