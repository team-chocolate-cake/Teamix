package com.chocolate.entities.channel

data class Channel(
    val channelId: Int,
    val channelName: String,
    val description: String,
    val invitationONly: Boolean,
    val topics: List<Topic>
)
