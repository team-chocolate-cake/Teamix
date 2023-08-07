package com.chocolate.repository.dto.remote.message.response

import com.google.gson.annotations.SerializedName

data class DisplayRecipient(
    @SerializedName("email")
    val email: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_mirror_dummy")
    val isMirrorDummy: Boolean?
)