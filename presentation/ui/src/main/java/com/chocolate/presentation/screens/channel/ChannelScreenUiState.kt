package com.chocolate.presentation.screens.channel

import com.chocolate.presentation.screens.topic_details.ReactionUiState

data class ChannelScreenUiState(
    val topics: List<TopicUiSate> = emptyList()
)

data class TopicUiSate(
    val creatorName:String,
    val creatorImage: Int,
    val topicName: String,
    val topicCreationDate: String,
    val replayImages : List<Int> = emptyList(),
    val reactions: List<ReactionUiState> = emptyList(),
)
