package com.chocolate.remote.users.request

import com.google.gson.annotations.SerializedName

data class CreateUserGroupSubgroupRequest(
    @SerializedName("add")
    val add: List<Int>
)