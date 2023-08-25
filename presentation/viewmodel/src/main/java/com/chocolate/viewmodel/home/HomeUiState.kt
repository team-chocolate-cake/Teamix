package com.chocolate.viewmodel.home

import com.chocolate.viewmodel.base.BaseViewModel

data class HomeUiState(
    val organizationTitle: String = "",
    val imageUrl: String = "",
    val badgeCountsUiState: BadgeCountsUiState = BadgeCountsUiState(),
    val channels: List<ChannelUiState> = emptyList(),
    val showNoInternetLottie: Boolean = false,
    val isLogged: Boolean = false,
    val isLoading: Boolean = true,
    val role: String = "",
    val error: String? = null
): BaseViewModel.BaseUiState

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