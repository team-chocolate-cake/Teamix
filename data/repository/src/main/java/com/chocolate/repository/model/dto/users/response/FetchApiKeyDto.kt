package com.chocolate.repository.model.dto.users.response


import com.google.gson.annotations.SerializedName

data class FetchApiKeyDto(
    @SerializedName("api_key")
    val apiKey: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("user_id")
    val userId: Int?
)