package com.chocolate.entities.channel

import com.chocolate.entities.topic.Topic

data class Channel(
    val id: String,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val membersId:List<String>
)


