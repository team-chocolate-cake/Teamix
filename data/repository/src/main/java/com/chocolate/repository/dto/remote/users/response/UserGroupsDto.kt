package com.chocolate.repository.dto.remote.users.response


import com.google.gson.annotations.SerializedName

data class UserGroupsDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_groups")
    val userGroups: List<com.chocolate.repository.dto.remote.users.response.UserGroup>?
)