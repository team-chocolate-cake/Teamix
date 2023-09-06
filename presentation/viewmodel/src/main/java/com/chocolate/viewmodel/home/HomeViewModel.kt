package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.UnAuthorizedException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.user.User
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.profile.toOwnerUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatus: GetUserLoginStatusUseCase,
    private val manageChannels: ManageChannelsUseCase,
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
    private val getCurrentUserData: GetCurrentUserDataUseCase,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    init {
        getData()
        isDarkTheme()
    }

    private fun isDarkTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            customizeProfileSettings.isDarkThem().collectLatest { isDark ->
                _state.update { it.copy(isDarkTheme = isDark) }
            }
        }
    }

    private fun getData() {
        getUserLoginState()
        //getOrganizationName()
        //getOrganizationImage()
        getChannels()
        getCurrentUserData()
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

    override fun onClickChannel(id: Int, name: String) {
        sendUiEffect(HomeUiEffect.NavigateToChannel(id, name))
    }

    override fun onClickTopic(name: String) {
        sendUiEffect(HomeUiEffect.NavigateToTopic(name))
    }

    override fun onClickFloatingActionButton() {
        sendUiEffect(HomeUiEffect.NavigateToCreateChannel)
    }

    override fun onClickRetryButton() {
        getData()
    }


    private fun getCurrentUserData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            getCurrentUserData::getRemoteCurrentUser,
            ::onGetCurrentUserDataSuccess,
            ::onGetCurrentUserDataError
        )
    }

    private fun onGetCurrentUserDataSuccess(user: User) {
        _state.update { it.copy(role = user.toOwnerUserUiState().role, isLoading = false) }
    }

    private fun onGetCurrentUserDataError(throwable: Throwable) {
        onError(throwable)
    }

    /*private fun getOrganizationImage() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageOrganizationDetails::getOrganizationImage,
            ::onGettingOrganizationImageSuccess,
            ::onError
        )
    }*/

    private fun onGettingOrganizationImageSuccess(image: String) {
        _state.update {
            it.copy(
                imageUrl = image,
                showNoInternetLottie = false,
                isLoading = false,
                error = null
            )
        }
    }

    /*private fun getOrganizationName() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageOrganizationDetails::getOrganizationName,
            ::onGettingOrganizationNameSuccess,
            ::onError
        )
    }*/

    private fun onGettingOrganizationNameSuccess(organizationName: String) {
        _state.update {
            it.copy(
                organizationTitle = organizationName,
                showNoInternetLottie = false,
                isLoading = false,
                error = null
            )
        }
    }

    private fun getChannels() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageChannels::getAllChannels,
            ::onGettingChannelsSuccess,
            ::onError
        )
    }

    private fun onGettingChannelsSuccess(channels: List<Channel>) {
        _state.update { it.copy(channels = channels.toUiState(), error = null, isLoading = false) }
    }

    private fun getUserLoginState() {
        viewModelScope.launch {
            getUserLoginStatus().collect { islogged ->
                if (islogged) {
                    _state.update { it.copy(isLogged = islogged) }
                } else {
                    launch(Dispatchers.Main) {
                        sendUiEffect(HomeUiEffect.NavigateToOrganizationName)
                    }
                }
            }
        }
    }

    private fun onError(throwable: Throwable) {
        when (throwable) {
            is UnAuthorizedException, is ValidationException ->
                sendUiEffect(HomeUiEffect.NavigateToOrganizationName)

            is NoConnectionException ->
                _state.update {
                    it.copy(
                        showNoInternetLottie = true,
                        isLoading = false,
                        error = throwable.message
                    )
                }
        }
        _state.update { it.copy(isLoading = false) }
    }
}