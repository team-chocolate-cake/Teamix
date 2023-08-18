package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.AuthenticationMethodsEntity
import com.chocolate.entities.server_and_organizations.ExternalAuthenticationMethodEntity
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.repository.model.dto.server_and_organizations.response.ServerSettingsDto

fun ServerSettingsDto.toEntity(): ServerSettings {
    val authenticationMethodsEntity = AuthenticationMethodsEntity(
        dev = authenticationMethodsDto?.dev ?: false,
        email = authenticationMethodsDto?.email ?: true,
        github = authenticationMethodsDto?.github ?: false,
        google = authenticationMethodsDto?.google ?: false,
        ldap = authenticationMethodsDto?.ldap ?: false,
        password = authenticationMethodsDto?.password ?: true,
        remoteUser = authenticationMethodsDto?.remoteUser ?: false,
    )

    val externalAuthenticationMethodsEntity =
        externalAuthenticationMethodDtos?.map { externalAuthDto ->
            ExternalAuthenticationMethodEntity(
                displayIcon = externalAuthDto.displayIcon ?: "",
                displayName = externalAuthDto.displayName ?: "",
                loginUrl = externalAuthDto.loginUrl ?: "",
                name = externalAuthDto.name ?: "",
                signupUrl = externalAuthDto.signupUrl ?: ""
            )
        } ?: emptyList()

    return ServerSettings(
        authenticationMethods = authenticationMethodsEntity,
        emailAuthEnabled = emailAuthEnabled ?: true,
        externalAuthenticationMethods = externalAuthenticationMethodsEntity,
        isIncompatible = isIncompatible ?: false,
        realmIcon = realmIcon ?: "",
        pushNotificationsEnabled = pushNotificationsEnabled ?: false
    )
}