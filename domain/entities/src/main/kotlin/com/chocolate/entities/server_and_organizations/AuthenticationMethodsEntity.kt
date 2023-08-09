package com.chocolate.entities.server_and_organizations

data class AuthenticationMethodsEntity(
    val dev: Boolean,
    val email: Boolean,
    val github: Boolean,
    val google: Boolean,
    val ldap: Boolean,
    val password: Boolean,
    val remoteUser: Boolean,
)