package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class CreateUserDto(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_id")
    val userId: Int?
)