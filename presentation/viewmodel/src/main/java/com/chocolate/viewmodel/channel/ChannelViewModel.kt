package com.chocolate.viewmodel.channel

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
):BaseViewModel<ChannelUiState,Unit>(ChannelUiState())