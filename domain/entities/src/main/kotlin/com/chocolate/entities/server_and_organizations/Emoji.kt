package com.chocolate.entities.server_and_organizations

data class CustomEmoji(
    val emoji: Map<String, Emoji>
)
data class Emoji(
    val authorId: Int,
    val deactivated: Boolean,
    val id: String,
    val name: String,
    val sourceUrl: String
)