package com.chocolate.repository.model.dto.users.response

import com.google.gson.annotations.SerializedName

data class MessageDto(
    @SerializedName("date_sent")
    val dateSent: Long?,
    @SerializedName("id")
    val id: Int?
)