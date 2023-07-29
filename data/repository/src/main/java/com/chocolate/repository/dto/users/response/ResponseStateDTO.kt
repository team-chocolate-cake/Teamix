package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class ResponseStateDTO(
    @SerializedName("code")
    val code: String?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)