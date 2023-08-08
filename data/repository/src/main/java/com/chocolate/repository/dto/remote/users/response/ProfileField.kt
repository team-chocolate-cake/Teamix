package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class ProfileField(
    @SerializedName("rendered_value")
    val renderedValue: String? = null,
    @SerializedName("value")
    val value: String?
)