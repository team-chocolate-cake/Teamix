package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.topic.Topic
import com.chocolate.usecases.channel.ManageTopicsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicsUseCase: ManageTopicsUseCase
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
            flowBlock = { manageTopicsUseCase.getTopicsInChannel(channelArgs.channelId.toString()) },
            onSuccess = ::onGetTopicsSuccess,
            onError = {}
        )
    }


    private suspend fun onGetTopicsSuccess(topics: Flow<List<Topic>>) {
        topics.collect { topics ->
            _state.update { it.copy(channelId = channelArgs.channelId.toString(),isLoading = false, topics = topics.toUiState()) }
        }
    }

    override fun onClickSeeAll(channelId:Int,topicId: String, topicName: String) {
        sendUiEffect(ChannelUiEffect.NavigateToTopicDetails(channelId,topicId, topicName))
    }

}