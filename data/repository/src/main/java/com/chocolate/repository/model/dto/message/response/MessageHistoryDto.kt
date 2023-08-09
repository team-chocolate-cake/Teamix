package com.chocolate.repository.model.dto.message.response

import com.google.gson.annotations.SerializedName

data class MessageHistoryDto(
    @SerializedName("content")
    val content: String?,
    @SerializedName("content_html_diff")
    val contentHtmlDiff: String?,
    @SerializedName("prev_content")
    val prevContent: String?,
    @SerializedName("prev_rendered_content")
    val prevRenderedContent: String?,
    @SerializedName("prev_topic")
    val prevTopic: String?,
    @SerializedName("rendered_content")
    val renderedContent: String?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("topic")
    val topic: String?,
    @SerializedName("user_id")
    val userId: Int?
)