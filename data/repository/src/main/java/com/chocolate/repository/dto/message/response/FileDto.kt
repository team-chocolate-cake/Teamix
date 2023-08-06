package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class FileDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("uri")
    val uri: String?
)