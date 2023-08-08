package com.chocolate.repository.dto.remote.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class CustomProfileFieldsDto(
    @SerializedName("custom_fields")
    val customFieldDtos: List<CustomFieldDto>?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("result")
    val result: String?
)