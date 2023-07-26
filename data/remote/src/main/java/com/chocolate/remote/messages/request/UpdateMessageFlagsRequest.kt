package com.chocolate.remote.messages.request

data class UpdateMessageFlagsRequest(
    val messages: List<Int>,
    val op: String,
    val flag: String
)
