package com.chocolate.remote.server_and_organizations.service

data class ProfileFieldRequest(
    val name: String,
    val hint: String,
    val field_type: Int
)
