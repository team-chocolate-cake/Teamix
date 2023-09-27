package com.chocolate.entities

import com.chocolate.entities.Member
import java.util.Date

data class SavedLaterMessage(
    val id: String,
    val sender: Member,
    val messageContent: String,
    val date: Date
)