package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class PersonalMessageForNarrowDto(
    @SerializedName("first_processed_id")
    val firstProcessedId: Int?,
    @SerializedName("found_newest")
    val foundNewest: Boolean?,
    @SerializedName("found_oldest")
    val foundOldest: Boolean?,
    @SerializedName("last_processed_id")
    val lastProcessedId: Int?,
    @SerializedName("msg")
    val message: String?,
    @SerializedName("processed_count")
    val processedCount: Int?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("updated_count")
    val updatedCount: Int?
)