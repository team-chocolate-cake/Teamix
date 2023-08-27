package com.chocolate.entities.draft

import java.util.Date

data class Draft (
    val id: Int,
    val targetAudienceIDs: List<Int>,
    val content: String,
    val timestamp: Date,
    val topic: String,
    val isInStream: Boolean
)