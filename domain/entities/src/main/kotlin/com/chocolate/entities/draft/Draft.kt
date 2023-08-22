package com.chocolate.entities.draft

data class Draft (
    val id: Int,
    val targetAudienceIDs: List<Int>, //1 target stream ID for "stream" messages; array of target user IDs for direct messages.
    val content: String,
    val timestamp: Double,
    val topic: String,
)
