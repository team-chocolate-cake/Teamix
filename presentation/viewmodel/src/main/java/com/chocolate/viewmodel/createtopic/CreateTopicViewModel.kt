package com.chocolate.viewmodel.createtopic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.channel.ManageTopicsUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicsUseCase: ManageTopicsUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase
) :
    BaseViewModel<CreateTopicUiState, CreateTopicEffect>(CreateTopicUiState()),
    CreateTopicInteraction {

    private val topicArgs = CreateTopicArgs(savedStateHandle)

    override fun onTopicNameChange(name: String) {
        _state.update { it.copy(name = name) }
    }

    override fun onTopicContentChange(content: String) {
        _state.update { it.copy(content = content) }
    }

    override fun onCreateClick() {
        viewModelScope.launch {
            tryToExecute(
                {
                    manageTopicsUseCase.createTopic(
                        channelId = topicArgs.channelId,
                        topic = state.value.toTopic(getCurrentMemberUseCase())
                    )
                },
                ::onCreateTopicSuccess,
                ::onCreateTopicError
            )
        }


    }

    private fun onCreateTopicSuccess(topicId: String) {
        sendUiEffect(
            CreateTopicEffect.NavigateToTopicScreen(
                topicId = topicId,
                channelId = topicArgs.channelId,
                topicName = state.value.name
            )
        )
    }

    private fun onCreateTopicError(e: Throwable) {
        _state.update { it.copy(message = e.message) }
    }

    override fun onErrorDismiss() {
        _state.update { it.copy(message = null) }
    }
}