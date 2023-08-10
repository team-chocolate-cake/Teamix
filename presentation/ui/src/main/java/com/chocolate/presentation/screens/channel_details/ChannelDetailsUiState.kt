package com.chocolate.presentation.screens.channel_details

import androidx.annotation.DrawableRes

data class ChannelDetailsUiState(
    val channelName: String = "Channel Name",
    val channelMuted: Boolean = false,
    val memberUiState: List<MemberUiState> = emptyList(),
    val pinCount: Int = 0,
    val isLoading:Boolean = false,
    val error : String? = null
)
data class MemberUiState(
    val name: String = "",
    val Image: String = "",
)
