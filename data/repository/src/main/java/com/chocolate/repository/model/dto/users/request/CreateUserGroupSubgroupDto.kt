package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class CreateUserGroupSubgroupDto(
    @SerializedName("add")
    val add: List<Int>
)