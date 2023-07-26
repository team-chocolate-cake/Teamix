package com.chocolate.remote.messages.request

import com.google.gson.annotations.SerializedName

data class EmojiReactionRequest(
    @SerializedName("message_id")
    val messageId: Int,
    @SerializedName("emoji_name")
    val emoji_name: String
)
