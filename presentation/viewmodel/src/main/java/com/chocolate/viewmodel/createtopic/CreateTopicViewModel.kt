package com.chocolate.viewmodel.createtopic

import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.util.InvalidTopicNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.usecase.member.GetCurrentMemberUseCase
import com.chocolate.usecases.usecase.topic.ManageTopicUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateTopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicUseCase: ManageTopicUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<CreateTopicUiState, CreateTopicEffect>(CreateTopicUiState()),
    CreateTopicInteraction {

    private val topicArgs = CreateTopicArgs(savedStateHandle)

    override fun onTopicNameChange(name: String) {
        _state.update { it.copy(name = name) }
    }

    override fun onTopicContentChange(content: String) {
        _state.update { it.copy(content = content) }
    }

    override fun onCreateClick() {
        tryToExecute(
            {
                manageTopicUseCase.validateTopicName(state.value.name)
            },
            ::onValidateNameSuccess,
            ::onCreateTopicError
        )
    }

    private fun onValidateNameSuccess(unit: Unit) {

        tryToExecute(
            {
                manageTopicUseCase.createTopic(
                    channelId = topicArgs.channelId,
                    topic = state.value.toTopic(getCurrentMemberUseCase())
                )
            },
            ::onCreateTopicSuccess,
            ::onCreateTopicError
        )
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
        val errorMessage = when (e) {
            is InvalidTopicNameException -> stringsResource.invalidTopicName
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = errorMessage) }
    }

    override fun onErrorDismiss() {
        _state.update { it.copy(error = null) }
    }
}