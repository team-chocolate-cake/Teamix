package com.chocolate.repository.model.dto.message

import java.util.Date

data class SavedLaterMessageDto(
    val id: String? = null,
    val senderId: String? = null,
    val messageContent: String? = null,
    val date: Date? = null
)
