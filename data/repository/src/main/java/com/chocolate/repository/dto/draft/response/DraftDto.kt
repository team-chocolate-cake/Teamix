package com.chocolate.repository.dto.draft.response


import com.google.gson.annotations.SerializedName

data class DraftDto(
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("timestamp")
    val timestamp: Double,
    @SerializedName("to")
    val to: List<Int>,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("type")
    val type: String
)