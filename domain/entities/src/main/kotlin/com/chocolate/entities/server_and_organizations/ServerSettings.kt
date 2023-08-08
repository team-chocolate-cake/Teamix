package com.chocolate.entities.server_and_organizations

data class ServerSettings(
    val authenticationMethods: AuthenticationMethodsEntity?,
    val emailAuthEnabled: Boolean?,
    val externalAuthenticationMethods: List<ExternalAuthenticationMethodEntity>?,
    val isIncompatible: Boolean?,
    val pushNotificationsEnabled: Boolean?,
)
data class AuthenticationMethodsEntity(
    val dev: Boolean?,
    val email: Boolean?,
    val github: Boolean?,
    val google: Boolean?,
    val ldap: Boolean?,
    val password: Boolean?,
    val remoteUser: Boolean?,
)
data class ExternalAuthenticationMethodEntity(
    val displayIcon: String,
    val displayName: String,
    val loginUrl: String,
    val name: String,
    val signupUrl: String
)