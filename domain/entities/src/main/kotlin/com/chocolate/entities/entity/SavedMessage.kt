package com.chocolate.entities.entity

import java.util.Date

data class SavedMessage(
    val id: String,
    val sender: Member,
    val messageContent: String,
    val date: Date
)