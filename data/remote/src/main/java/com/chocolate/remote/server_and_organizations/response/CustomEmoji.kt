package com.chocolate.remote.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class CustomEmoji(
    @SerializedName("emoji")
    val emoji: Emoji,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
) {
    data class Emoji(
        @SerializedName("1")
        val x1: X1
    ) {
        data class X1(
            @SerializedName("author_id")
            val authorId: Int,
            @SerializedName("deactivated")
            val deactivated: Boolean,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("source_url")
            val sourceUrl: String
        )
    }
}