package com.chocolate.entities.channel

import com.chocolate.entities.topic.Topic

data class Channel(
    val id: Int,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val topics: List<Topic>,
    val isCurrentUserSubscribed: Boolean,
    val isMuted: Boolean,
)