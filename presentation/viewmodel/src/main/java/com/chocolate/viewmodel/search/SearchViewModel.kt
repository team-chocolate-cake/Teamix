package com.chocolate.viewmodel.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.entity.Channel
import com.chocolate.entities.util.BlankSearchQueryException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.entities.util.Empty
import com.chocolate.usecases.search.SearchForChannelUseCase
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchForChannelUseCase: SearchForChannelUseCase,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
    ) : BaseViewModel<SearchUiState, SearchEffect>(SearchUiState()), SearchInteraction {

    init {
        collectFlow(state.value.query.debounce(1000).distinctUntilChanged()) {
            tryToExecute(
                { searchForChannelUseCase(it) },
                ::onChangeSearchChannelsQuerySuccess,
                ::onError
            )
            this
        }
        isDarkTheme()
    }

    override fun onClickChannelItem(id: String, name: String) {
        sendUiEffect(SearchEffect.NavigateToChannel(id.toInt(), name))
    }

    override fun onChangeSearchQuery(query: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch { _state.value.query.emit(query) }
    }

    override fun onClickRetry() {
        _state.update { it.copy(isLoading = true) }
        onChangeSearchQuery(_state.value.query.value)
    }

    override fun onClickDeleteQuery() {
        viewModelScope.launch { _state.value.query.emit(String.Empty) }
        _state.update { it.copy(channelsUiState = emptyList()) }
    }

    private fun onChangeSearchChannelsQuerySuccess(channels: Flow<List<Channel>>) {
        collectFlow(channels) {
            this.copy(
                channelsUiState = it.toUiState(),
                isChannelsEmpty = it.isEmpty(),
                isLoading = false,
                showNoInternetLottie = false,
                error = null,
            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false) }
        Log.e("onError: ", throwable.toString())
        when (throwable) {
            is NoConnectionException -> _state.update { it.copy(showNoInternetLottie = true) }
            is BlankSearchQueryException -> onClickDeleteQuery()
        }
    }

    private fun isDarkTheme() {
        collectFlow(customizeProfileSettings.isDarkThemeEnabled()) {
            this.copy(isDarkTheme = it)
        }
    }
}