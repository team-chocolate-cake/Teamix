package com.chocolate.remote.server_and_organizations.requestsDto


import com.google.gson.annotations.SerializedName

data class UpdateRealmLevelDefaultsOfUserSettingsDto(
    @SerializedName("ignored_parameters_unsupported")
    val ignoredParametersUnsupported: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)