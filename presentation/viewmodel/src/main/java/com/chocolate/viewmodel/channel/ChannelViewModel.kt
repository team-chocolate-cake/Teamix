package com.chocolate.viewmodel.channel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.channel.Topic
import com.chocolate.usecases.channel.GetTopicsInChannelById
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.login.LoginArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTopicsInChannelById: GetTopicsInChannelById
) : BaseViewModel<ChannelScreenUiState, ChannelEvents>(ChannelScreenUiState()) {
    private val channelArgs = ChannelArgs(savedStateHandle)

    init {
        getTopics()
    }

    private fun getTopics(){
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getTopicsInChannelById(channelArgs.channelId) },
            onSuccess = ::onGetTopicsSuccess,
            onError = {}
        )
    }

    private fun onGetTopicsSuccess(topics: List<Topic>) {
        _state.update { it.copy(isLoading = false , topics = topics.toUiState()) }
    }

}