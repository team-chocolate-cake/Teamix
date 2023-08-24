package com.chocolate.viewmodel.createChannel

data class CreateChannelUiState(
    val nameInput: String = "",
    val description: String? = null,
    val status: ChannelStatus = ChannelStatus.Private
)
