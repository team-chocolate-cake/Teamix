package com.chocolate.presentation.screens.channel

import com.chocolate.presentation.screens.topic_details.ReactionUiState

data class ChannelScreenUiState(
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
