package com.chocolate.remote.server_and_organizations.requests

data class ProfileFieldRequest(
    val name: String,
    val hint: String,
    val field_type: Int
)
