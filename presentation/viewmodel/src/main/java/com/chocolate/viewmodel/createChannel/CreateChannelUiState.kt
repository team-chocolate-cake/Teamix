package com.chocolate.viewmodel.createChannel

import com.chocolate.entities.uills.Empty
import com.chocolate.viewmodel.base.BaseErrorUiState

data class CreateChannelUiState(
    val nameInput: String = String.Empty,
    val description: String? = null,
    val isPrivate: Boolean = true,
    val status: ChannelStatus = ChannelStatus.Private,
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)