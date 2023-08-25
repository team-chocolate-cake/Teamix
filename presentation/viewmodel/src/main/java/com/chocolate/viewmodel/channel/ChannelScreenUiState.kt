package com.chocolate.viewmodel.channel

import com.chocolate.entities.channel.Topic
import com.chocolate.viewmodel.topic.ReactionUiState
import com.chocolate.viewmodel.topic.TopicUiState

data class ChannelScreenUiState(
    val channelName: String = "",
    val topics: List<TopicState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TopicState(
    val id: Int = 0,
    val creatorName: String = "",
    val creatorImage: String = "",
    val topicName: String = "",
    val topicCreationDate: String = "",
    val replayImages: List<Int> = emptyList(),
    val reactions: List<ReactionUiState> = emptyList(),
)

fun List<Topic>.toUiState(): List<TopicState> =
    map {
        TopicState(
            id = it.id,
            topicName = it.name,
            creatorName = "Marwan",
            creatorImage = "https://image.tmdb.org/t/p/original/4t0oBFrJyweYPt0hocW6RUa0b6H.jpg",
            topicCreationDate = "6:08"
        )
    }
