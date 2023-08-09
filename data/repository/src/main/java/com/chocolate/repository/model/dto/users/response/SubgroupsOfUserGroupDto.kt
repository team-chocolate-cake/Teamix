package com.chocolate.repository.model.dto.users.response

import com.google.gson.annotations.SerializedName

data class SubgroupsOfUserGroupDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("subgroups")
    val subgroups: List<Int>?
)