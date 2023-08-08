package com.chocolate.repository.dto.channels.response

import com.google.gson.annotations.SerializedName

data class DefaultStreamDto(

    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("msg")
    val message: String? = null,
    @field:SerializedName("result")
    val result: String? = null
)
