package com.chocolate.viewmodel.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.usecases.channel.GetSubscribedChannelsUseCase
import com.chocolate.usecases.organization.GetNameOrganizationsUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
    private val getSubscribedChannelsUseCase: GetSubscribedChannelsUseCase,
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getUserLoginState()
            tryToExecute(
                { getNameOrganizationsUseCase() },
                ::onGettingOrganizationNameSuccess,
                ::onError
            )
            tryToExecute({ getSubscribedChannelsUseCase() }, ::onGettingChannelsSuccess, ::onError)
        }
    }

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