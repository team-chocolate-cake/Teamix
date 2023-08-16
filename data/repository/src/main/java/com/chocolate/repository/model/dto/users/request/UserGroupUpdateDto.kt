package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class UserGroupUpdateDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)