package com.chocolate.repository.dto.remote.users.response

import com.google.gson.annotations.SerializedName

data class UserSettingsDto(
    @SerializedName("ignored_parameters_unsupported")
    val ignoredParametersUnsupported: List<String>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)