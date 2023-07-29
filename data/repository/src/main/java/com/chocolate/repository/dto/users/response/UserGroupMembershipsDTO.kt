package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class UserGroupMembershipsDTO(
    @SerializedName("members")
    val members: List<Int>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)