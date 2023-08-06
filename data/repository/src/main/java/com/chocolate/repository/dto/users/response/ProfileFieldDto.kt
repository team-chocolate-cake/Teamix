package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class ProfileFieldDto(
    @SerializedName("rendered_value")
    val renderedValue: String? = null,
    @SerializedName("value")
    val value: String?
)