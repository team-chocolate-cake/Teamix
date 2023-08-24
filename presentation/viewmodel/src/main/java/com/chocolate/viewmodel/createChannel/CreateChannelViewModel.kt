package com.chocolate.viewmodel.createChannel

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(

) : BaseViewModel<CreateChannelUiState, CreateChannelUiEffect>(CreateChannelUiState()),
    CreateChannelInteraction {
    override fun onCreateChannelClicked() {
        //todo : create channel then navigate to Choose Members screen
        // ** create channel code here **
        sendUiEffect(effect = CreateChannelUiEffect.NavigationToChooseMembers)
    }

    override fun onChannelNameTextChange(newChannelName: String) {
        _state.update { it.copy(nameInput = newChannelName) }
    }

    override fun onChannelDescriptionChange(newChannelDescription: String?) {
        _state.update { it.copy(description = newChannelDescription) }
    }

    override fun onChannelStatusChange(newChannelStatus: ChannelStatus) {
        _state.update { it.copy(status = newChannelStatus) }
    }


}