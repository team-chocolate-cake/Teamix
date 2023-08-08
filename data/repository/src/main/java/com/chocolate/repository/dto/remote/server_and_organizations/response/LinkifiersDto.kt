package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class LinkifiersDto(
    @SerializedName("linkifiers")
    val linkifiers: List<Linkifier>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)