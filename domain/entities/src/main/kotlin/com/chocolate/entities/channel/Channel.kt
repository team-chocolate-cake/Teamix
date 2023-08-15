package com.chocolate.entities.channel

data class Channel(
    val streamId: Int,
    val streamName: String,
    val description: String,
    val invitationONly: Boolean
)
