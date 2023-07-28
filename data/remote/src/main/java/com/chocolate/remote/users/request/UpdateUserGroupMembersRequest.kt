package com.chocolate.remote.users.request

import com.google.gson.annotations.SerializedName

data class UpdateUserGroupMembersRequest(
    @SerializedName("delete")
    val delete: List<Int>,
    @SerializedName("add")
    val add: List<Int>
)