package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.usecases.channel.GetChannelsUseCase
import com.chocolate.usecases.channel.GetSubscribedChannelsUseCase
import com.chocolate.usecases.organization.GetNameOrganizationsUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
    private val getSubscribedChannelsUseCase: GetSubscribedChannelsUseCase,
    private val getChannelsUseCase: GetChannelsUseCase,
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000)
            getUserLoginState()
            onGettingOrganizationName()
            onGettingChannels()
        }
    }

    private fun onGettingOrganizationName() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getNameOrganizationsUseCase() },
            ::onGettingOrganizationNameSuccess,
            ::onError
        )
    }

    private fun onGettingOrganizationNameSuccess(organizationName: String) {
        _state.update {
            it.copy(
                isLoading = false,
                organizationTitle = organizationName,
                error = null
            )
        }
    }

    private fun onGettingChannels() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ getSubscribedChannelsUseCase() }, ::onGettingChannelsSuccess, ::onError)
    }

    private fun onGettingChannelsSuccess(channels: List<Channel>) {
        _state.update { it.copy(isLoading = false, channels = channels.toUiState(), error = null) }
    }

    private suspend fun getUserLoginState() {
        collectFlow(getUserLoginStatusUseCase()) { this.copy(isLogged = it) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }
}