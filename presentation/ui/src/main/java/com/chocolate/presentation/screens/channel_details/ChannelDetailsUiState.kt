package com.chocolate.presentation.screens.channel_details

data class ChannelDetailsUiState(
    val channelName: String = "",
    val channelMuted: Boolean = false,
    val memberUiState: List<ChannelMemberUiState> = emptyList(),
    val pinCount: Int = 0,
    val isLoading:Boolean = false,
    val error : String? = null
)
data class ChannelMemberUiState(
    val name: String = "",
    val Image: String = "",
)
