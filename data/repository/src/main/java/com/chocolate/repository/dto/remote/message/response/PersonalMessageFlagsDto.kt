package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class PersonalMessageFlagsDto(
    @SerializedName("messages")
    val messages: List<Int>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)