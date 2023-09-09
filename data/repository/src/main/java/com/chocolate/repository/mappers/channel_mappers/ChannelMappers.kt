package com.chocolate.repository.mappers.channel_mappers

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.ChannelType
import com.chocolate.repository.model.dto.channels.ChannelDto

@JvmName("channelDtoToChannel")
fun ChannelDto.toEntity() = Channel(
    id = id!!,
    name = name ?: "",
    description = description ?: "",
    channelType = ChannelType.valueOf(channelType.toString()),
    topics = emptyList(),
    membersId = emptyList()
)

@JvmName("ChannelsDtoToChannels")
fun List<ChannelDto>.toEntity(): List<Channel> = this.map { it.toEntity() }

@JvmName("channelToChannelDto")
fun Channel.toRemote() = ChannelDto(
    id = id,
    name = name,
    description = description,
    channelType = channelType.toString(),
    topics = emptyList(),
    membersId = emptyList()
)

@JvmName("ChannelsToChannelsDto")
fun List<Channel>.toRemote(): List<ChannelDto> = this.map { it.toRemote() }