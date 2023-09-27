package com.chocolate.viewmodel.channel

import com.chocolate.entities.utils.Empty
import com.chocolate.viewmodel.topicmessages.ReactionUiState

data class ChannelUiState(
    val channelName: String = String.Empty,
    val topics: List<TopicUiSate> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TopicUiSate(
    val creatorName: String = String.Empty,
    val creatorImage: String = String.Empty,
    val topicName: String = String.Empty,
    val topicCreationDate: String = String.Empty,
    val replayImages: List<Int> = emptyList(),
    val reactions: List<ReactionUiState> = emptyList(),
)

