package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class CreateUserDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("full_name")
    val fullName: String
)