package com.chocolate.repository.dto.remote.users.response


import com.google.gson.annotations.SerializedName

data class CreateUserDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val message: String?,
    @SerializedName("user_id")
    val userId: Int?
)