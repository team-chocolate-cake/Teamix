package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class StatusUpdate(
    @SerializedName("status_text")
    val status_text: String? = null,
    @SerializedName("emoji_name")
    val emoji_name: String? = null,
    @SerializedName("emoji_code")
    val emoji_code: String? = null,
    @SerializedName("reaction_type")
    val reaction_type: String? = null,
    @SerializedName("away")
    val away: Boolean? = null
)