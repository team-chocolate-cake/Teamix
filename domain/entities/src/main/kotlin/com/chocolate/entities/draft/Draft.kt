package com.chocolate.entities.draft

data class Draft (
    val id: Int,
    val targetAudienceIDs: List<Int>,
    val content: String,
    val timestamp: Double,
    val topic: String,
    val isInStream: Boolean
)
