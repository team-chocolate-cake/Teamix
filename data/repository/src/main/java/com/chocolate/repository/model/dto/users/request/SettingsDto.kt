package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class SettingsDto(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("email")
    val email: String,
)