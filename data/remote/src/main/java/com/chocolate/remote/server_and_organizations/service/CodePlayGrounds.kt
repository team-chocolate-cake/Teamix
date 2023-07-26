package com.chocolate.remote.server_and_organizations.service

import com.google.gson.annotations.SerializedName

data class CodePlayGrounds(
    @SerializedName("name")
    val name: String,
    @SerializedName("pygments_language")
    val language: String,
    @SerializedName("url_prefix")
    val url: String
)
