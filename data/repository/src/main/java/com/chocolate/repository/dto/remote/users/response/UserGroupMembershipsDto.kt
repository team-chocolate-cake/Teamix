package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class UserGroupMembershipsDto(
    @SerializedName("members")
    val members: List<Int>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)