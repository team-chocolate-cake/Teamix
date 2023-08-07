package com.chocolate.repository.dto.remote.draft.request

data class DraftRequest(
    val id: Int? = null,
    val type: String,
    val to: List<Int>,
    val topic: String,
    val content: String,
    val timestamp: Long? = null
)