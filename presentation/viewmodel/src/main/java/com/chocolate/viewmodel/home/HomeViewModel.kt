package com.chocolate.viewmodel.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.usecases.channel.GetChannelsUseCase
import com.chocolate.usecases.channel.GetSubscribedChannelsUseCase
import com.chocolate.usecases.channel.LeaveChannelUseCase
import com.chocolate.usecases.organization.GetNameOrganizationsUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
    private val getSubscribedChannelsUseCase: GetSubscribedChannelsUseCase,
    private val getChannelsUseCase: GetChannelsUseCase,
    private val leaveChannelUseCase: LeaveChannelUseCase,
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch { getUserLoginState() }
        tryToExecute(
            { getNameOrganizationsUseCase() },
            ::onGettingOrganizationNameSuccess,
            ::onError
        )
        tryToExecute({ getChannelsUseCase() }, ::onGettingChannelsSuccess, ::onError)
        tryToExecute(
            { leaveChannelUseCase(state.value.channels[1].name) },
            ::onLeaveChannelSuccess, ::onError
        )
    }

    private fun onLeaveChannelSuccess(isLeave: Boolean) { if (isLeave) getData() }

    private fun onGettingOrganizationNameSuccess(organizationName: String) {
        _state.update { it.copy(isLoading = false, organizationTitle = organizationName) }
    }

    private fun onGettingChannelsSuccess(channels: List<Channel>) {
        _state.update { it.copy(isLoading = false, channels = channels.toUiState()) }
    }

    private fun onError(throwable: Throwable) = Log.e("onError: ", throwable.message.toString())

    private suspend fun getUserLoginState() {
        collectFlow(getUserLoginStatusUseCase()) { this.copy(isLogged = it) }
    }
}