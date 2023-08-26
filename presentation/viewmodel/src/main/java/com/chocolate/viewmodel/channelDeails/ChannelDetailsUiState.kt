package com.chocolate.viewmodel.channelDeails

import com.chocolate.entities.uills.Empty

data class ChannelDetailsUiState(
    val channelName: String = String.Empty,
    val channelMuted: Boolean = false,
    val memberUiState: List<ChannelMemberUiState> = emptyList(),
    val pinCount: Int = 0,
    val isLoading:Boolean = false,
    val error : String? = null
)

data class ChannelMemberUiState(
    val name: String = String.Empty,
    val image: String = String.Empty,
)
