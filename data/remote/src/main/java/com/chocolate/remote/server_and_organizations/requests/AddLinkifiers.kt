package com.chocolate.remote.server_and_organizations.requests

import com.google.gson.annotations.SerializedName

data class AddLinkifiers(
    @SerializedName("pattern")
    val pattern: String,
    @SerializedName("url_template")
    val url: String
)
