package com.chocolate.repository.dto.server_and_organizations.response

import com.google.gson.annotations.SerializedName

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