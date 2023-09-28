package com.chocolate.viewmodel.createchannel

import com.chocolate.entities.util.Empty

data class CreateChannelUiState(
    val channelName: String = String.Empty,
    val description: String? = null,
    val isPrivate: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)