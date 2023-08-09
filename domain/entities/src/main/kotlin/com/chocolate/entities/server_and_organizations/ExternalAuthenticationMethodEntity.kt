package com.chocolate.entities.server_and_organizations

data class ExternalAuthenticationMethodEntity(
    val displayIcon: String,
    val displayName: String,
    val loginUrl: String,
    val name: String,
    val signupUrl: String
)