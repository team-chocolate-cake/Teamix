package com.chocolate.viewmodel.channel

import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.channel.Topic
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageChannels: ManageChannelsUseCase
) : BaseViewModel<ChannelScreenUiState, ChannelUiEffect>(ChannelScreenUiState()),
    ChannelInteraction {
    private val channelArgs = ChannelArgs(savedStateHandle)

    init {
        _state.update { it.copy(channelName = channelArgs.channelName) }
        getTopics()
    }

    private fun getTopics() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { manageChannels.getTopicsInChannel(channelArgs.channelId) },
            onSuccess = ::onGetTopicsSuccess,
            onError = {}
        )
    }

    private fun onGetTopicsSuccess(topics: List<Topic>) {
        _state.update { it.copy(isLoading = false, topics = topics.toUiState()) }
    }

    override fun onClickSeeAll(topicName: String) {
        sendUiEffect(ChannelUiEffect.NavigateToTopicDetails(topicName))
    }

}