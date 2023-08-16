package com.chocolate.entities.server_and_organizations

data class ServerSettings(
    val authenticationMethods: AuthenticationMethodsEntity,
    val emailAuthEnabled: Boolean,
    val externalAuthenticationMethods: List<ExternalAuthenticationMethodEntity>,
    val isIncompatible: Boolean,
    val pushNotificationsEnabled: Boolean,
)