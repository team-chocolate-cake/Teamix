package com.chocolate.repository.dto.message.response


import com.google.gson.annotations.SerializedName

data class PersonalMessageFlags(
    @SerializedName("messages")
    val messages: List<Int?>?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)