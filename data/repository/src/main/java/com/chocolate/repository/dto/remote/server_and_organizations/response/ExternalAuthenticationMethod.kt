package com.chocolate.repository.dto.remote.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class ExternalAuthenticationMethod(
    @SerializedName("display_icon")
    val displayIcon: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("login_url")
    val loginUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("signup_url")
    val signupUrl: String
)