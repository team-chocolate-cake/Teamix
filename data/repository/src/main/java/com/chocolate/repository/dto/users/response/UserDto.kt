package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user")
    val userDetailsDto: UserDetailsDto?
)