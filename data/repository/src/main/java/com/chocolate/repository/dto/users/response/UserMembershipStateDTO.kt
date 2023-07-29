package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserMembershipStateDTO(
    @SerializedName("is_user_group_member")
    val isUserGroupMember: Boolean,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)