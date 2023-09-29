package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.entity.Topic
import com.chocolate.usecases.topic.ManageTopicUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicUseCase: ManageTopicUseCase
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

    override fun onSaveTopic(topic: TopicState) {
        viewModelScope.launch {
            manageTopicUseCase.addSavedTopic(topic.toTopic())
        }
    }
}