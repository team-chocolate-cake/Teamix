package com.chocolate.repository.model.dto.users.request

import com.google.gson.annotations.SerializedName

data class StatusUpdateDto(
    @SerializedName("status_text")
    val statusText: String,
    @SerializedName("away")
    val isAway: Boolean,
    @SerializedName("emoji_name")
    val emojiName: String,
    @SerializedName("emoji_code")
    val emojiCode: String,
    @SerializedName("reaction_type")
    val reactionType: String
)
