package com.chocolate.viewmodel.search

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.uills.Empty
import com.chocolate.entities.user.User
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.usecases.user.GetAllUsersUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.home.toChannelsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val manageChannels: ManageChannelsUseCase,
    private val getUsers: GetAllUsersUseCase
): BaseViewModel<SearchUiState,SearchEffect>(SearchUiState()),SearchInteraction {
    private var searchJob: Job? = null

    override fun onClickChannelItem(channelId: Int) {
        sendUiEffect(SearchEffect.NavigateToChannel(channelId))
    }

    override fun onClickMemberItem(memberId: Int) {
        sendUiEffect(SearchEffect.NavigateToMember(memberId))
    }

    override fun onChangeSearchQuery(query: String) {
        _state.update { it.copy(isLoading = true, query = query) }
        onSearch()
    }

    override fun onChangeTabIndex(tabIndex: Int) {
        _state.update {
            it.copy(
                currentTabIndex = tabIndex,
                query = String.Empty,
                channelsUiState = emptyList(),
                membersUiState = emptyList()
            )
        }
    }

    override fun onClickRecentSearchItem(text: String) {
        _state.update { it.copy(query = text) }
    }

    override fun onClickRetry() {
        _state.update { it.copy(isLoading = true) }
        onChangeSearchQuery(_state.value.query)
    }

    private fun onSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            when (_state.value.currentTabIndex) {
                0 -> onSearchChannels()
                1 -> onSearchMembers()
            }
        }
    }

    private fun onSearchMembers() {
        tryToExecute(
            { getUsers.searchUser(_state.value.query) },
            ::onChangeSearchUsersQuerySuccess,
            ::onChangeSearchQueryError
        )
    }

    private fun onChangeSearchUsersQuerySuccess(users: List<User>) {
        _state.update {
            it.copy(
                membersUiState = users.toMembersUiState(),
                isLoading = false,
                showNoInternetLottie = false,
                error = null,
            )
        }
    }

    private fun onSearchChannels() {
        tryToExecute(
            { manageChannels.searchChannels(_state.value.query) },
            ::onChangeSearchChannelsQuerySuccess,
            ::onChangeSearchQueryError
        )
    }

    private fun onChangeSearchChannelsQuerySuccess(channels: List<Channel>) {
        _state.update {
            it.copy(
                channelsUiState = channels.toChannelsUiState(),
                isLoading = false,
                showNoInternetLottie = false,
                error = null,
            )
        }
    }

    private fun onChangeSearchQueryError(throwable: Throwable) {
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