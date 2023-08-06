package com.chocolate.viewmodel.home

data class HomeUiState(
    val title: String = "",
    val imageUrl: String = "",
    val badgeCountsUiState: BadgeCountsUiState = BadgeCountsUiState(),
    val channelsUIState: List<ChannelUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class BadgeCountsUiState(
    val mentions: Int = 0,
    val drafts: Int = 0,
    val starred: Int = 0,
    val saved: Int = 0
)

data class ChannelUIState(
    val channelId: Int = 0,
    val name: String = "",
    val topics: List<TopicsUIState> = emptyList(),
    val isPrivateChannel: Boolean = false,
)

data class TopicsUIState(
    val name: String = "",
    val topicBadge: Int = 0
)