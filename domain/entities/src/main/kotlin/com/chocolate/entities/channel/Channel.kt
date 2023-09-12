package com.chocolate.entities.channel

import com.chocolate.entities.topic.Topic

data class Channel(
    val id: String,
    val name: String,
    val description: String,
    val channelType: ChannelType,
    val topics: List<Topic>,
    val membersId:List<String>
)