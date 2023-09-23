package com.chocolate.entities.messages

import com.chocolate.entities.member.Member
import java.util.Date

data class SavedLaterMessage(
    val id: String,
    val sender: Member,
    val messageContent: String,
    val date: Date
)