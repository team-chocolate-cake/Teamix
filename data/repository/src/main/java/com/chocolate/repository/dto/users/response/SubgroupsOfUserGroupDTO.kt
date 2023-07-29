package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class SubgroupsOfUserGroupDTO(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("subgroups")
    val subgroups: List<Int>
)