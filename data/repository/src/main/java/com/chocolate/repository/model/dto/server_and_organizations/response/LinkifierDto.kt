package com.chocolate.repository.model.dto.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class LinkifierDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("pattern")
    val pattern: String?,
    @SerializedName("url_template")
    val urlTemplate: String?
)