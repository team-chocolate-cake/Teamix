package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class UsersDto(
    @SerializedName("members")
    val members: List<com.chocolate.repository.dto.remote.users.response.Member?>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)