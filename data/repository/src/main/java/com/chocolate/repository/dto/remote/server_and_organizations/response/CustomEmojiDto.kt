package com.chocolate.repository.dto.remote.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class CustomEmojiDto(
    @SerializedName("emoji")
    val emojiDto: Map<String, EmojiDto>?,
    @SerializedName("msg")
    val message: String,
    @SerializedName("result")
    val result: String
)