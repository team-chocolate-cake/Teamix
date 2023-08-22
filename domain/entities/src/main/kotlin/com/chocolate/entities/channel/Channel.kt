package com.chocolate.entities.channel

data class Channel(
    val id: Int,
    val name: String,
    val description: String,
    val invitationOnly: Boolean,
    val topics: List<Topic>,
    val isCurrentUserSubscribed: Boolean,
    val isMuted: Boolean,
)
