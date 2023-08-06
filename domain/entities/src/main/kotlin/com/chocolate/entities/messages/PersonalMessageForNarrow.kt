package com.chocolate.entities.messages

data class PersonalMessageForNarrow(
    val firstProcessedId: Int?,
    val foundNewest: Boolean?,
    val foundOldest: Boolean?,
    val lastProcessedId: Int?,
    val processedCount: Int?,
    val updatedCount: Int?
)
