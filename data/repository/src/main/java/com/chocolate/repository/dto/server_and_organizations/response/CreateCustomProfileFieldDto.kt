package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class CreateCustomProfileFieldDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)