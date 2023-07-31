package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class ServerSettingsDto(
    @SerializedName("authentication_methods")
    val authenticationMethods: AuthenticationMethods?,
    @SerializedName("email_auth_enabled")
    val emailAuthEnabled: Boolean?,
    @SerializedName("external_authentication_methods")
    val externalAuthenticationMethods: List<ExternalAuthenticationMethod>?,
    @SerializedName("is_incompatible")
    val isIncompatible: Boolean?,
    @SerializedName("msg")
    val msg: String?,
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
) {
    data class AuthenticationMethods(
        @SerializedName("azuread")
        val azuread: Boolean?,
        @SerializedName("dev")
        val dev: Boolean?,
        @SerializedName("email")
        val email: Boolean?,
        @SerializedName("github")
        val github: Boolean?,
        @SerializedName("google")
        val google: Boolean?,
        @SerializedName("ldap")
        val ldap: Boolean?,
        @SerializedName("password")
        val password: Boolean?,
        @SerializedName("remoteuser")
        val remoteuser: Boolean?,
        @SerializedName("saml")
        val saml: Boolean?
    )

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
}