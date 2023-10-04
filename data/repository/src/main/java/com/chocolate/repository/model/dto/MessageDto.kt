package com.chocolate.repository.model.dto

import java.util.Date

data class MessageDto(
    val id: String? = null,
    val content: String? = null,
    val userId: String? = null,
    val senderName: String? = null,
    val senderImage: String? = null,
    val timestamp: Date? = null,
)
