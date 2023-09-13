package com.chocolate.viewmodel.search

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.uills.Empty
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.usecases.channel.SearchForChannelUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val manageChannels: ManageChannelsUseCase,
    private val searchForChannelUseCase: SearchForChannelUseCase
    ): BaseViewModel<SearchUiState,SearchEffect>(SearchUiState()),SearchInteraction {


    override fun onClickChannelItem(id: String, name: String) {
        sendUiEffect(SearchEffect.NavigateToChannel(id.toInt(),name))
    }

    override fun onChangeSearchQuery(query: String) {
        _state.update { it.copy(isLoading = true, query = query) }
        onSearchChannels()
    }

    override fun onClickRetry() {
        _state.update { it.copy(isLoading = true) }
        onChangeSearchQuery(_state.value.query)
    }

    override fun onClickDeleteQuery() {
        _state.update { it.copy(query = String.Empty, channelsUiState = emptyList()) }
    }

    private fun onSearchChannels() {

        tryToExecute(
            { searchForChannelUseCase(_state.value.query) },
            ::onChangeSearchChannelsQuerySuccess,
            ::onError
        )
    }

    private fun onChangeSearchChannelsQuerySuccess(channels: Flow<List<Channel>>) {
        collectFlow(channels) {
            this.copy(
                channelsUiState = it.toUiState(),
                isLoading = false,
                showNoInternetLottie = false,
                error = null,
            )
        }
    }

    private fun onError(throwable: Throwable) {
        val showNoInternetLottie = throwable is NoConnectionException
        _state.update {
            it.copy(
                isLoading = false,
                error = throwable.message,
                showNoInternetLottie = showNoInternetLottie
            )
        }
    }
}