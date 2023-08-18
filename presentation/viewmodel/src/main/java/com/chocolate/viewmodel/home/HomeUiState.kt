package com.chocolate.viewmodel.home

data class HomeUiState(
    val organizationTitle: String = "",
    val imageUrl: String = "",
    val badgeCountsUiState: BadgeCountsUiState = BadgeCountsUiState(),
    val channels: List<ChannelUiState> = emptyList(),
    val isLogged: Boolean = true,
    val isLoading: Boolean = true,
    val error: String? = null
)

data class BadgeCountsUiState(
    val mentions: Int = 0,
    val drafts: Int = 0,
    val starred: Int = 0,
    val saved: Int = 0
)

data class ChannelUiState(
    val channelId: Int = 0,
    val name: String = "",
    val topics: List<TopicUiState> = emptyList(),
    val isPrivateChannel: Boolean = false,
)

data class TopicUiState(
    val name: String = "",
    val topicBadge: Int = 0
)