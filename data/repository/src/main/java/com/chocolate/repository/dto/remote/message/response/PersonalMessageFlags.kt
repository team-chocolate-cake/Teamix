package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class PersonalMessageFlags(
    @SerializedName("messages")
    val messages: List<Int?>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)