package com.chocolate.repository.dto.remote.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class ServerSettingsDto(
    @SerializedName("authentication_methods")
    val authenticationMethodsDto: AuthenticationMethodsDto?,
    @SerializedName("email_auth_enabled")
    val emailAuthEnabled: Boolean?,
    @SerializedName("external_authentication_methods")
    val externalAuthenticationMethodDtos: List<ExternalAuthenticationMethodDto>?,
    @SerializedName("is_incompatible")
    val isIncompatible: Boolean?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("push_notifications_enabled")
    val pushNotificationsEnabled: Boolean?,
    @SerializedName("realm_description")
    val realmDescription: String?,
    @SerializedName("realm_icon")
    val realmIcon: String?,
    @SerializedName("realm_name")
    val realmName: String?,
    @SerializedName("realm_uri")
    val realmUri: String?,
    @SerializedName("realm_web_public_access_enabled")
    val realmWebPublicAccessEnabled: Boolean?,
    @SerializedName("require_email_format_usernames")
    val requireEmailFormatUsernames: Boolean?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("zulip_merge_base")
    val zulipMergeBase: String?,
    @SerializedName("zulip_version")
    val zulipVersion: String?
)