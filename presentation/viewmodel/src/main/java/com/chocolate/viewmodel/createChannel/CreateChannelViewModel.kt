package com.chocolate.viewmodel.createChannel

import com.chocolate.usecases.channel.AddUsersInChannelByChannelNameAndUsersIdUseCase
import com.chocolate.viewmodel.base.BaseErrorUiState
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateChannelViewModel @Inject constructor(
    private val createChannelUseCase: AddUsersInChannelByChannelNameAndUsersIdUseCase
) : BaseViewModel<CreateChannelUiState, CreateChannelUiEffect>(CreateChannelUiState()),
    CreateChannelInteraction {

    override fun onCreateChannelClicked() {
        tryToExecute(
            call = ::createChannel,
            onError = ::onCreateChannelError,
            onSuccess = ::onCreateChannelSuccess
        )
    }

    private suspend fun createChannel(): Boolean {
        _state.update { it.copy(isLoading = true) }
        return createChannelUseCase(
            channelName = _state.value.nameInput,
            usersId = listOf(),
            description = _state.value.description,
            isPrivate = _state.value.isPrivate
        )
    }

    private fun onCreateChannelSuccess(isCreatedSuccessfully: Boolean) {
        _state.update {
            it.copy(isLoading = false, error = BaseErrorUiState(message = null, isError = false))
        }
        if (isCreatedSuccessfully) {
            sendUiEffect(effect = CreateChannelUiEffect.NavigationToChooseMembers(channelName = _state.value.nameInput))
        }
    }

    private fun onCreateChannelError(throwable: Throwable) {
        _state.update {
            it.copy(error = BaseErrorUiState(
                    message = throwable.message,
                    isError = true
                ), isLoading = false
            )
        }
    }

    override fun onChannelNameTextChange(newChannelName: String) {
        _state.update { it.copy(nameInput = newChannelName) }
    }

    override fun onChannelDescriptionChange(newChannelDescription: String?) {
        _state.update { it.copy(description = newChannelDescription) }
    }

    override fun onChannelStatusChange(newChannelStatus: ChannelStatus, isPrivate: Boolean) {
        _state.update { it.copy(status = newChannelStatus, isPrivate = isPrivate) }
    }


}