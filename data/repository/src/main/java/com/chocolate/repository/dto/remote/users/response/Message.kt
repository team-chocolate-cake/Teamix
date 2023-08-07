package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("date_sent")
    val dateSent: Long?,
    @SerializedName("id")
    val id: Int?
)