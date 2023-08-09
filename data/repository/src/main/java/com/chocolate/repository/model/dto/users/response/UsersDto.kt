package com.chocolate.repository.model.dto.users.response

import com.google.gson.annotations.SerializedName

data class UsersDto(
    @SerializedName("members")
    val members: List<Member?>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)