package com.chocolate.repository.mappers.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.repository.model.dto.channel.ChannelDto

fun ChannelDto.toChannel() = Channel(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    isPrivate = isPrivate ?: false,
    membersId = membersId ?: emptyList(),
)

fun List<ChannelDto>?.toChannel(): List<Channel> = this?.map { it.toChannel() } ?: emptyList()

fun Channel.toChannelDto() = ChannelDto(
    id = id,
    name = name,
    description = description,
    isPrivate = isPrivate,
    membersId = membersId,
)

fun List<Channel>?.toChannelDto(): List<ChannelDto> = this?.map { it.toChannelDto() } ?: emptyList()