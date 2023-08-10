package com.chocolate.entities.channel_models

data class StreamItem(
    val streamId: Int,
    val streamName: String,
    val description: String,
    val invitationONly: Boolean
)
