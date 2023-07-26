package com.chocolate.remote.server_and_organizations.service

import com.google.gson.annotations.SerializedName

data class AddLinkifiers(
    @SerializedName("pattern")
    val pattern: String,
    @SerializedName("url_template")
    val url: String
)
