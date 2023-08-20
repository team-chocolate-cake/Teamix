package com.chocolate.repository.model.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserStatus(
    @SerializedName("away")
    val away: Boolean?,
    @SerializedName("emoji_code")
    val emojiCode: String?,
    @SerializedName("emoji_name")
    val emojiName: String?,
    @SerializedName("reaction_type")
    val reactionType: String?,
    @SerializedName("status_text")
    val statusText: String?
)