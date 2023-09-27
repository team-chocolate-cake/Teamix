package com.chocolate.viewmodel.createchannel

import com.chocolate.entities.utils.Empty

data class CreateChannelUiState(
    val channelName: String = String.Empty,
    val description: String? = null,
    val isPrivate: Boolean = true,
    val status: ChannelStatus = ChannelStatus.Private,
    val isLoading: Boolean = false,
    val message: String? = null,
    val isError: Boolean = false
)