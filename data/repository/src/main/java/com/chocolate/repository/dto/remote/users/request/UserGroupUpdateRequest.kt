package com.chocolate.repository.dto.remote.users.request

import com.google.gson.annotations.SerializedName

data class UserGroupUpdateRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)