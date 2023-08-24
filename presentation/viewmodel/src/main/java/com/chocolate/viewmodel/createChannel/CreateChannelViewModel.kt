package com.chocolate.viewmodel.createChannel

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(

) : BaseViewModel<CreateChannelUiState, CreateChannelUiEffect>(CreateChannelUiState()),
    CreateChannelInteraction {


}