package com.chocolate.viewmodel.channel

import com.chocolate.entities.util.Empty

data class ChannelScreenUiState(
    val channelName: String = String.Empty,
    val channelId: String = String.Empty,
    val topics: List<TopicState> = emptyList(),
    val isLoading: Boolean = false,
    val savedTopicState: String? = null,
    val error: String? = null
)

data class TopicState(
    val id: String = String.Empty,
    val creatorName: String = String.Empty,
    val creatorImage: String = String.Empty,
    val topicContent: String = String.Empty,
    val sentTime: String = String.Empty,
)


