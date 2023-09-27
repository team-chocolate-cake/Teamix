package com.chocolate.viewmodel.channeldetails

import com.chocolate.entities.util.Empty

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
