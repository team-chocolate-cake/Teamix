package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.InvalidURlHostException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.server_and_organizations.ServerSettings
import com.chocolate.usecases.channel.GetChannelsUseCase
import com.chocolate.usecases.channel.GetSubscribedChannelsUseCase
import com.chocolate.usecases.organization.GetImageOrganizationUseCase
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
    private val getImageOrganizationUseCase: GetImageOrganizationUseCase,
    private val getChannelsUseCase: GetChannelsUseCase,
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()),HomeInteraction {

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getUserLoginState()
            onGettingOrganizationName()
            onGettingOrganizationImage()
            onGettingChannels()
        }
    }

    private fun onGettingOrganizationImage() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getImageOrganizationUseCase() },
            ::onGettingOrganizationImageSuccess,
            ::onError
        )
    }

    private fun onGettingOrganizationImageSuccess(serverSettings: ServerSettings) {
        _state.update {
            it.copy(
                isLoading = true,
                imageUrl = serverSettings.realmIcon,
                showNoInternetLottie = false,
                error = null
            )
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
                isLoading = true,
                organizationTitle = organizationName,
                showNoInternetLottie = false,
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
        collectFlow(getUserLoginStatusUseCase()) { this.copy(isLoading = true, isLogged = it) }
    }

    private fun onError(throwable: Throwable) {
        when (throwable) {
            is InvalidURlHostException, is ValidationException -> sendUiEffect(HomeUiEffect.NavigateToOrganizationName)
            is NoConnectionException -> _state.update {
                it.copy(
                    showNoInternetLottie = true,
                    isLoading = false
                )
            }
        }
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    override fun onClickDrafts() {
        sendUiEffect(HomeUiEffect.NavigationToDrafts)
    }

    override fun onClickStarred() {
        sendUiEffect(HomeUiEffect.NavigationToStarred)
    }

    override fun onClickSavedLater() {
        sendUiEffect(HomeUiEffect.NavigationToSavedLater)
    }

    override fun onClickChannel(id: Int) {
        sendUiEffect(HomeUiEffect.NavigateToChannel)
    }

    override fun onClickTopic(name: String) {
        sendUiEffect(HomeUiEffect.NavigateToTopic)
    }
}