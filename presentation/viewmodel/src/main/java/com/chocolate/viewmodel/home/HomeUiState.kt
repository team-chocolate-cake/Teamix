package com.chocolate.viewmodel.home

import com.chocolate.entities.util.Empty

data class HomeUiState(
    val organizationTitle: String = String.Empty,
    val imageUrl: String = String.Empty,
    val badgeCountsUiState: BadgeCountsUiState = BadgeCountsUiState(),
    val channels: List<ChannelUiState> = emptyList(),
    val showNoInternetLottie: Boolean = false,
    val isLogged: Boolean = false,
    val isLoading: Boolean = false,
    val isDarkTheme: Boolean = false,
    val role: String = String.Empty,
    val error: String? = null
)

data class BadgeCountsUiState(
    val mentions: Int = 0,
    val drafts: Int = 0,
    val starred: Int = 0,
    val saved: Int = 0
)

data class ChannelUiState(
    val channelId: String = String.Empty ,
    val name: String = String.Empty,
    val topics: List<TopicUiState> = emptyList(),
)

data class TopicUiState(
    val id: String = String.Empty,
    val name: String = String.Empty,
    val topicBadge: Int = 0
)