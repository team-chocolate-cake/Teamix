package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.entity.Topic
import com.chocolate.usecases.usecase.topic.ManageTopicUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicUseCase: ManageTopicUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<ChannelScreenUiState, ChannelUiEffect>(ChannelScreenUiState()),
    ChannelInteraction {
    private val channelArgs = ChannelArgs(savedStateHandle)

    init {
        _state.update {
            it.copy(
                channelName = channelArgs.channelName,
                channelId = channelArgs.channelId.toString()
            )
        }
        getTopics()
    }

    private fun getTopics() {
        _state.update { it.copy(isLoading = true) }
        tryToExecuteFlow(
            flowBlock = { manageTopicUseCase.getTopicsInChannel(channelArgs.channelId.toString()) },
            onSuccess = ::onGetTopicsSuccess,
            onError = {}
        )
    }

    private suspend fun onGetTopicsSuccess(topics: Flow<List<Topic>>) {
        topics.collect { topicsList ->
            _state.update {
                it.copy(
                    channelId = channelArgs.channelId.toString(),
                    isLoading = false,
                    topics = topicsList.toUiState()
                )
            }
        }
    }

    override fun onClickSeeAll(channelId: Int, topicId: String, topicName: String) {
        sendUiEffect(ChannelUiEffect.NavigateToTopicDetails(channelId, topicId, topicName))
    }

    override fun onAddTopicClick() {
        sendUiEffect(ChannelUiEffect.NavigateToCreateTopic(channelArgs.channelId.toString()))
    }

    override fun onSnackBarDismiss() {
        _state.update { it.copy(savedTopicState = null) }
    }

    override fun onSaveTopic(topic: TopicState) {
        tryToExecute(call = { manageTopicUseCase.addSavedTopic(topic.toTopic()) },
            onSuccess = { onSavedMessageSuccess() },
            onError = { onError(it) }
        )
    }

    private fun onSavedMessageSuccess() {
        _state.update { it.copy(error = null, savedTopicState = stringsResource.savedForLater) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }
}