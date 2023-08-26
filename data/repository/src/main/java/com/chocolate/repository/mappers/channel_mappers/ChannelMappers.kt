package com.chocolate.repository.mappers.channel_mappers

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.Topic
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionsItemDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.TopicsItemDto

const val SUCCESS = "success"

fun SubscriptionsItemDto.toEntity(
    topics: List<Topic>,
): Channel {
    return Channel(
        id = streamId ?: 0,
        name = name.orEmpty(),
        description = description.orEmpty(),
        isPrivate = inviteOnly ?: false,
        topics = topics,
        isCurrentUserSubscribed = true,
        isMuted = isMuted ?: false
    )
}

suspend fun SubscribedStreamDto.toEntity(getTopics: suspend (channelId: Int) -> List<Topic>): List<Channel> {
    return this.subscriptions?.map { it.toEntity(getTopics(it.streamId ?: 0)) } ?: emptyList()
}

fun List<StreamDto>?.toEntity(): List<Channel> =
    this?.map { it.toEntity() } ?: emptyList()

fun StreamDto.toEntity(): Channel {
    return Channel(
        id = this.streamId ?: 0,
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        isPrivate = this.inviteOnly ?: false,
        topics = emptyList(),
        isCurrentUserSubscribed = false,
        false
    )
}

fun DefaultStreamDto.toSuccessOrFail(): Boolean = this.result?.equals(SUCCESS) ?: false

fun TopicsItemDto.toEntity(): Topic = Topic(name = name.orEmpty(), id = maxId ?: 0)

fun TopicsInStreamDto.toEntity(): List<Topic> = topics?.map { it.toEntity() } ?: emptyList()