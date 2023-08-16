package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class DisplayRecipientDto(
    @SerializedName("email")
    val email: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_mirror_dummy")
    val isMirrorDummy: Boolean?
)