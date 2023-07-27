package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class PersonalMessageFlags(
    @SerializedName("messages")
    val messages: List<Int?>?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("result")
    val result: String?
)