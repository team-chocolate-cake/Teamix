package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class UpdateOrRemoveDto(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)