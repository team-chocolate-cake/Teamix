package com.chocolate.entities.messages

data class PersonalMessageForNarrow(
    val firstProcessedId: Int?,
    val foundNewest: Boolean?,
    val foundOldest: Boolean?,
    val lastProcessedId: Int?,
    val message: String?,
    val processedCount: Int?,
    val result: String?,
    val updatedCount: Int?
)
