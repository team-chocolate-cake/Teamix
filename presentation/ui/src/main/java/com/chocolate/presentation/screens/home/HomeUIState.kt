package com.chocolate.presentation.screens.home

data class HomeUIState(
    val title: String = "",
    val imageUrl: String = "",
    val chipsUIState: List<ChipsUIState> = emptyList(),
    val channelsUIState: List<ChannelUIState> = emptyList(),
    var isShowSheet: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)
data class ChipsUIState(
    val title: String = "",
    val notificationNumber: Int = 0,
    val icon: Int = 0,
)
data class ChannelUIState(
    val name: String = "",
    val topics: List<TopicsUIState> = emptyList(),
    var isExpanded: Boolean = false,
    val isPrivateChannel: Boolean = false,
)
data class TopicsUIState(
    val name: String = "",
    val notificationNumber: Int = 0
)