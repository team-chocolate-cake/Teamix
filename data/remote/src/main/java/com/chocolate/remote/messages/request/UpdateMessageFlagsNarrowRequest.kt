package com.chocolate.remote.messages.request

data class UpdateMessageFlagsNarrowRequest(
    val narrow: List<Map<String, String>>,
    val op: String,
    val flag: String
)