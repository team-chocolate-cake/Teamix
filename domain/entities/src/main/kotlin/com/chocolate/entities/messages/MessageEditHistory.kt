package com.chocolate.entities.messages

data class MessageEditHistory(
    val content: String,
    val prevContent: String,
    val prevRenderedContent: String,
    val prevTopic: String,
    val renderedContent: String,
    val timestamp: Int,
    val topic: String,
    val userId: Int,
)
