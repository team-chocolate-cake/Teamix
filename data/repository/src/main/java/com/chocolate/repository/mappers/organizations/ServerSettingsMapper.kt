package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.AuthenticationMethodsEntity
import com.chocolate.entities.server_and_organizations.ExternalAuthenticationMethodEntity
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.dto.remote.server_and_organizations.response.ServerSettingsDto

fun ServerSettingsDto.toEntity(): ServerSettings {
    val authenticationMethodsEntity = AuthenticationMethodsEntity(
        dev = authenticationMethods?.dev,
        email = authenticationMethods?.email,
        github = authenticationMethods?.github,
        google = authenticationMethods?.google,
        ldap = authenticationMethods?.ldap,
        password = authenticationMethods?.password,
        remoteUser = authenticationMethods?.remoteUser,
    )

    val externalAuthenticationMethodsEntity =
        externalAuthenticationMethods?.map { externalAuthDto ->
            ExternalAuthenticationMethodEntity(
                displayIcon = externalAuthDto.displayIcon,
                displayName = externalAuthDto.displayName,
                loginUrl = externalAuthDto.loginUrl,
                name = externalAuthDto.name,
                signupUrl = externalAuthDto.signupUrl
            )
        } ?: emptyList()

    return ServerSettings(
        authenticationMethods = authenticationMethodsEntity,
        emailAuthEnabled = emailAuthEnabled,
        externalAuthenticationMethods = externalAuthenticationMethodsEntity,
        isIncompatible = isIncompatible,
        pushNotificationsEnabled = pushNotificationsEnabled
    )
}