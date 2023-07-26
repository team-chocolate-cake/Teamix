package com.chocolate.remote.server_and_organizations.requestsDto


import com.google.gson.annotations.SerializedName

data class LinkifiersDto(
    @SerializedName("linkifiers")
    val linkifiers: List<Linkifier>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
) {
    data class Linkifier(
        @SerializedName("id")
        val id: Int,
        @SerializedName("pattern")
        val pattern: String,
        @SerializedName("url_template")
        val urlTemplate: String
    )
}