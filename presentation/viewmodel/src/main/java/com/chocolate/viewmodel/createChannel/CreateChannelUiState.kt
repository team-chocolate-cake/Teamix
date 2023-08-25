package com.chocolate.viewmodel.createChannel

import com.chocolate.viewmodel.base.BaseErrorUiState
import com.chocolate.viewmodel.base.BaseViewModel

data class CreateChannelUiState(
    val nameInput: String = "",
    val description: String? = null,
    val isPrivate: Boolean = true,
    val status: ChannelStatus = ChannelStatus.Private,
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
): BaseViewModel.BaseUiState
