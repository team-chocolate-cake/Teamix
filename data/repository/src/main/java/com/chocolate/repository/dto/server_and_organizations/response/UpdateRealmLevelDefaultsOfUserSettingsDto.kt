package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class UpdateRealmLevelDefaultsOfUserSettingsDto(
    @SerializedName("ignored_parameters_unsupported")
    val ignoredParametersUnsupported: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
)