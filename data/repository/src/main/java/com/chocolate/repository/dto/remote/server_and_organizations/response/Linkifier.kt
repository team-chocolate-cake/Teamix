package com.chocolate.repository.dto.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class Linkifier(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("pattern")
    val pattern: String?,
    @SerializedName("url_template")
    val urlTemplate: String?
)