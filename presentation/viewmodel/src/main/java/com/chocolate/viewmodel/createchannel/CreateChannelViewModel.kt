package com.chocolate.viewmodel.createchannel

import com.chocolate.entities.util.ChannelAlreadyExistException
import com.chocolate.entities.util.InvalidChannelNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.channel.ManageChannelUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(
    private val manageChannelUseCase: ManageChannelUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<CreateChannelUiState, CreateChannelUiEffect>(CreateChannelUiState()),
    CreateChannelInteraction {
    override fun onNextClicked() {
        tryToExecute(
            { manageChannelUseCase.validateChannelName(state.value.channelName) },
            ::onValidateNameSuccess,
            ::onError
        )
    }

    override fun onChannelNameTextChange(channelName: String) {
        _state.update { it.copy(channelName = channelName) }
    }

    override fun onChannelDescriptionChange(channelDescription: String) {
        _state.update { it.copy(description = channelDescription) }
    }

    override fun onSnackBarDismiss() {
        _state.update { it.copy(errorMessage = null) }
    }

    private fun onValidateNameSuccess(channelName: String) {
        _state.update { it.copy(isLoading = false) }
        sendUiEffect(
            effect = CreateChannelUiEffect.NavigationToChooseMembers(
                channelName = _state.value.channelName,
                description = _state.value.description,
            )
        )
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is ChannelAlreadyExistException -> stringsResource.channelAlreadyExist
            is InvalidChannelNameException -> stringsResource.invalidChannelName
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(errorMessage = errorMessage, isLoading = false) }
    }
}