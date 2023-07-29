package com.chocolate.remote.drafts.request

data class DraftRequest(
    val id: Int? = null,
    val type: String,
    val to: List<Int>,
    val topic: String,
    val content: String,
    val timestamp: Long? = null
)