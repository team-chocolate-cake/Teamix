package com.chocolate.remote.users.request

import com.google.gson.annotations.SerializedName

data class UserGroupCreationRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("members")
    val members: List<Int>
)
