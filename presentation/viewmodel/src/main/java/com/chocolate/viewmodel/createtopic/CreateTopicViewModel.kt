package com.chocolate.viewmodel.createtopic

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.exceptions.EmptyTopicContentException
import com.chocolate.entities.exceptions.EmptyTopicNameException
import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateTopicViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val manageChannelsUseCase: ManageChannelsUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<CreateTopicUiState, CreateTopicEffect>(CreateTopicUiState()),
    CreateTopicInteraction {
    override fun onTopicNameChange(topicName: String) {
        _state.update { it.copy(topicName = topicName) }
    }

    override fun onTopicContentChange(content: String) {
        _state.update { it.copy(content = content) }
    }

    override fun onCreateClick() {
        val channelName = CreateTopicArgs(savedStateHandle).channelName
        Log.e("onCreateClick: ", "called")
        tryToExecute({
            manageChannelsUseCase.createNewTopic(
                _state.value.topicName,
                channelName,
                _state.value.content
            )
        }, ::onCreateChannelSuccess, ::onError)
    }

    private fun onCreateChannelSuccess(messageId: Int) {
        if (messageId > 0) {
            _state.update { it.copy(isLoading = false, stringError = null) }
            sendUiEffect(CreateTopicEffect.NavigateToTopicScreen(_state.value.topicName))
        }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is EmptyTopicContentException -> stringsResource.topicContentCannotBeEmpty
            is EmptyTopicNameException -> stringsResource.topicCannotBeEmpty
            is NoConnectionException -> stringsResource.noConnectionMessage
            is NetworkException -> stringsResource.enterValidEmailAddress
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(isLoading = false, stringError = error) }
    }

}