package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.UnAuthorizedException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.user.User
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.profile.toOwnerUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatus: GetUserLoginStatusUseCase,
    private val manageChannels: ManageChannelsUseCase,
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
    private val getCurrentUserData: GetCurrentUserDataUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {

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
            getCurrentUserData()
        }
    }

    private fun getCurrentUserData() {
        tryToExecute(
            { getCurrentUserData.getRemoteCurrentUser() },
            ::onGetCurrentUserDataSuccess,
            ::onGetCurrentUserDataError
        )
    }

    private fun onGetCurrentUserDataSuccess(user: User) {
        val userUiState = user.toOwnerUserUiState()
        _state.update { it.copy(role = userUiState.role) }
    }

    private fun onGetCurrentUserDataError(throwable: Throwable) {
        onError(throwable)
    }

    private fun onGettingOrganizationImage() {
        tryToExecute(
            { manageOrganizationDetails.getOrganizationImage() },
            ::onGettingOrganizationImageSuccess,
            ::onError
        )
    }

    private fun onGettingOrganizationImageSuccess(Image: String) {
        _state.update {
            it.copy(
                imageUrl = Image,
                showNoInternetLottie = false,
                error = null
            )
        }
    }

    private fun onGettingOrganizationName() {
        tryToExecute(
            { manageOrganizationDetails.getOrganizationName() },
            ::onGettingOrganizationNameSuccess,
            ::onError
        )
    }

    private fun onGettingOrganizationNameSuccess(organizationName: String) {
        _state.update {
            it.copy(
                organizationTitle = organizationName,
                showNoInternetLottie = false,
                error = null
            )
        }
    }

    private fun onGettingChannels() {
        tryToExecute({ manageChannels.getSubscribedChannels() }, ::onGettingChannelsSuccess, ::onError)
    }

    private fun onGettingChannelsSuccess(channels: List<Channel>) {
        _state.update { it.copy(channels = channels.toUiState(), error = null, isLoading = false) }
    }

    private fun getUserLoginState() {
        viewModelScope.launch {
            collectFlow(getUserLoginStatus()) {
                this.copy(isLogged = it)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        when (throwable) {
            is UnAuthorizedException, is ValidationException ->
                sendUiEffect(HomeUiEffect.NavigateToOrganizationName)

            is NoConnectionException -> _state.update {
                it.copy(
                    showNoInternetLottie = true,
                    isLoading = false,
                    error = throwable.message
                )
            }
        }
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

    override fun onClickFloatingActionButton() {
        sendUiEffect(HomeUiEffect.NavigateToCreateChannel)
    }
}