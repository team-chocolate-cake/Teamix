package com.chocolate.repository.mappers

import com.chocolate.entities.entity.Channel
import com.chocolate.repository.model.dto.ChannelDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("channelDtoToChannel")
fun ChannelDto.toEntity() = Channel(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    membersId = membersId ?: emptyList(),
)
@JvmName("channelsDtoToChannels")
fun List<ChannelDto>?.toEntity(): List<Channel> = this?.map { it.toEntity() } ?: emptyList()

@JvmName("flowOfChannelsDtoToFlowOfChannels")
fun Flow<List<ChannelDto>>.toEntity(): Flow<List<Channel>> = this.map { it.toEntity()  }

@JvmName("channelToChannelDto")
fun Channel.toRemote() = ChannelDto(
    id = id,
    name = name,
    description = description,
    membersId = membersId,
)

