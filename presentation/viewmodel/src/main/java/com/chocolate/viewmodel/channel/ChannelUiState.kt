package com.chocolate.viewmodel.channel

import com.chocolate.viewmodel.topic.ReactionUiState

data class ChannelUiState(
val channelName: String = "",
val topics: List<TopicUiSate> = emptyList(),
val isLoading: Boolean = false,
val error: String? = null
)

data class TopicUiSate(
    val creatorName: String = "",
    val creatorImage: String = "",
    val topicName: String = "",
    val topicCreationDate: String = "",
    val replayImages: List<Int> = emptyList(),
    val reactions: List<ReactionUiState> = emptyList(),
)

