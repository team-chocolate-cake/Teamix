package com.chocolate.repository.mappers.channel_mappers

import com.chocolate.entities.channel.Channel
import com.chocolate.repository.model.dto.channels.ChannelDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.utils.SUCCESS


fun DefaultStreamDto.toSuccessOrFail(): Boolean = this.result?.equals(SUCCESS) ?: false


fun ChannelDto.toChannel() = Channel(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    isPrivate = channelType ?: false,
    membersId = membersId?: emptyList(),
)
fun List<ChannelDto>?.toChannel(): List<Channel> = this?.map { it.toChannel() } ?: emptyList()


fun Channel.toChannelDto() = ChannelDto(
    id = id,
    name = name,
    description = description ,
    channelType = isPrivate ,
    membersId = membersId,
)

fun List<Channel>?.toChannelDto(): List<ChannelDto> = this?.map { it.toChannelDto() } ?: emptyList()
