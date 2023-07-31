package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserGroupMembersDto(
    @SerializedName("members")
    val members: List<Int>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)