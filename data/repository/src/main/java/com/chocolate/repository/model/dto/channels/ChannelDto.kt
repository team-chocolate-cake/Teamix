package com.chocolate.repository.model.dto.channels

import com.chocolate.entities.channel.Topic

data class ChannelDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val channelType: String? = null,
    val topics: List<Topic>? = null,
    val membersId:List<String>? = null,
)