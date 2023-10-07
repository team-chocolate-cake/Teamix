package com.chocolate.viewmodel.savedTopics

import com.chocolate.entities.util.Empty

data class SavedTopicsUiState(
    val channelName: String = String.Empty,
    val channelId: String = String.Empty,
    val topics: List<SavedTopicsItemUiState> = emptyList(),
    val deleteStateTopic: String? = null,
    val isLoading: Boolean = false,
    val savedTopicState:String? = null,
    val error: String? = null
)

data class SavedTopicsItemUiState(
    val id: String = String.Empty,
    val creatorName: String = String.Empty,
    val creatorImage: String = String.Empty,
    val topicContent: String = String.Empty,
    val sentTime: String = String.Empty,
)