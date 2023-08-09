package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class CreateUserGroupSubgroupRequest(
    @SerializedName("add")
    val add: List<Int>
)