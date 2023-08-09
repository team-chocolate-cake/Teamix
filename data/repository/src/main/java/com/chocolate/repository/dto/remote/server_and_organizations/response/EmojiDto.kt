package com.chocolate.repository.dto.remote.server_and_organizations.response

import com.google.gson.annotations.SerializedName

data class EmojiDto(
    @SerializedName("author_id")
    val authorId: Int?,
    @SerializedName("deactivated")
    val deactivated: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("source_url")
    val sourceUrl: String?
)