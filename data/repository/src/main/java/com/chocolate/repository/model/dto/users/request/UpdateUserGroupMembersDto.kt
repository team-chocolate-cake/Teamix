package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class UpdateUserGroupMembersDto(
    @SerializedName("delete")
    val delete: List<Int>,
    @SerializedName("add")
    val add: List<Int>
)