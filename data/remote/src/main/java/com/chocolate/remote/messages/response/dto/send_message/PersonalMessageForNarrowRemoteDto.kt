package com.chocolate.remote.messages.response.dto.send_message


import com.google.gson.annotations.SerializedName

data class PersonalMessageForNarrowRemoteDto(
    @SerializedName("first_processed_id")
    val firstProcessedId: Int?,
    @SerializedName("found_newest")
    val foundNewest: Boolean?,
    @SerializedName("found_oldest")
    val foundOldest: Boolean?,
    @SerializedName("last_processed_id")
    val lastProcessedId: Int?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("processed_count")
    val processedCount: Int?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("updated_count")
    val updatedCount: Int?
)