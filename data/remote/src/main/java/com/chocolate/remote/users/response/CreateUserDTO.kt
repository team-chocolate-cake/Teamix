package com.chocolate.remote.users.response


import com.google.gson.annotations.SerializedName

data class CreateUserDTO(
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_id")
    val userId: Int?
)