package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class Website(
    @SerializedName("status")
    val status: String?,
    @SerializedName("timestamp")
    val timestamp: Int?
)