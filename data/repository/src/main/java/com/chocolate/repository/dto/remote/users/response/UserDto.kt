package com.chocolate.repository.dto.remote.users.response


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user")
    val user: com.chocolate.repository.dto.remote.users.response.User?
)