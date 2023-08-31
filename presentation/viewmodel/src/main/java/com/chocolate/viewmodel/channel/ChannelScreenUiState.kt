package com.chocolate.viewmodel.channel

import com.chocolate.entities.channel.Topic
import com.chocolate.entities.uills.Empty

data class ChannelScreenUiState(
    val channelName: String = String.Empty,
    val topics: List<TopicState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TopicState(
    val id: Int = 0,
    val creatorName: String = String.Empty,
    val creatorImage: String = String.Empty,
    val topicName: String = String.Empty,
    val topicCreationDate: String = String.Empty,
    val replayImages: List<Int> = emptyList(),
)

fun List<Topic>.toUiState(): List<TopicState> =
    map {
        TopicState(
            id = it.id,
            topicName = it.name,
            creatorName = String.Empty,
            creatorImage = String.Empty,
            topicCreationDate = String.Empty
        )
    }
