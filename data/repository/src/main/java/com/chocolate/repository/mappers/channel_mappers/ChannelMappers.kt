package com.chocolate.repository.mappers.channel_mappers

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.Topic
import com.chocolate.repository.model.dto.channels.response.AllStreamsDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamDto
import com.chocolate.repository.model.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionsItemDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.TopicsItemDto

fun SubscriptionsItemDto.toEntity(): Channel {
    return Channel(
        channelId = streamId ?: 0,
        channelName = name ?: "",
        description = description ?: "",
        invitationONly = inviteOnly ?: false,
        emptyList()
    )
}

fun SubscribedStreamDto.toEntity(): List<Channel> {
    return this.subscriptions?.map { it.toEntity() } ?: emptyList()
}

fun StreamsByIdDto.toEntity(): Channel {
    return Channel(
        this.streamDto?.streamId ?: 0,
        this.streamDto?.name ?: "",
        this.streamDto?.description ?: "",
        this.streamDto?.inviteOnly ?: false,
        emptyList()
    )
}

fun AllStreamsDto.toEntity(): List<Channel> = this.streams?.map { it.toEntity() } ?: emptyList()

fun StreamDto.toEntity(): Channel {
    return Channel(
        streamId ?: 0,
        name ?: "",
        description ?: "",
        inviteOnly ?: false,
        emptyList()
    )
}

fun DefaultStreamDto.toSuccessOrFail(): Boolean = this.result?.equals("success") ?: false

fun TopicsItemDto.toEntity(): Topic = Topic(name ?: "", maxId ?: 0)

fun TopicsInStreamDto.toEntity(): List<Topic> = topics?.map { it.toEntity() } ?: emptyList()