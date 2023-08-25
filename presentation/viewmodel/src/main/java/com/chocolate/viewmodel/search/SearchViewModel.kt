package com.chocolate.viewmodel.search

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.user.User
import com.chocolate.usecases.channel.GetChannelsUseCase
import com.chocolate.usecases.user.GetUsersUseCase
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
    private val getChannelsUseCase: GetChannelsUseCase,
    private val getUsersUseCase: GetUsersUseCase
): BaseViewModel<SearchUiState,SearchEffect>(SearchUiState()),SearchInteraction {

    private var searchJob: Job? = null

    override fun onClickChannelItem(channelId: Int) {
        sendUiEffect(SearchEffect.NavigateToChannel(channelId))
    }

    override fun onClickMemberItem(memberId: Int) {
        TODO("Not yet implemented")
    }

    override fun onChangeSearchQuery(query: String) {
        _state.update { it.copy(isLoading = true, searchQuery = query) }
        onSearch()

    }

    private fun onSearch() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            onSearchChannels()
            onSearchMembers()
        }
    }

    private fun onSearchMembers() {
        tryToExecute(
            { getUsersUseCase.searchUser(_state.value.searchQuery) },
            ::onChangeSearchUsersQuerySuccess,
            ::onChangeSearchUsersQueryError
        )
    }

    private fun onChangeSearchUsersQuerySuccess(users: List<User>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                membersUiState = users.toMembersUiState()
            )
        }
    }

    private fun onChangeSearchUsersQueryError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    private fun onSearchChannels() {
        tryToExecute(
            { getChannelsUseCase.searchChannels(_state.value.searchQuery) },
            ::onChangeSearchChannelsQuerySuccess,
            ::onChangeSearchChannelsQueryError
        )
    }

    private fun onChangeSearchChannelsQuerySuccess(channels: List<Channel>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                channelsUiState = channels.toChannelsUiState()
            )
        }
    }

    private fun onChangeSearchChannelsQueryError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }
}