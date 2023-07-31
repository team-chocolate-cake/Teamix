package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class CustomEmojiDto(
    @SerializedName("emoji")
    val emoji: Map<String, Emoji>?,
    @SerializedName("msg")
    val message: String,
    @SerializedName("result")
    val result: String
)