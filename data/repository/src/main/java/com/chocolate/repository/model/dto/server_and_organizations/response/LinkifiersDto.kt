package com.chocolate.repository.model.dto.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class LinkifiersDto(
    @SerializedName("linkifiers")
    val linkifierDtos: List<LinkifierDto>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)