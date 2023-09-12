package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.UnAuthorizedException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.member.Member
import com.chocolate.usecases.channel.ManageChannelsUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.member.IsMemberLoggedInUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.profile.toOwnerUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatus: IsMemberLoggedInUseCase,
    private val manageChannels: ManageChannelsUseCase,
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    init {
        getData()
        isDarkTheme()
    }

    private fun isDarkTheme() {
        /*viewModelScope.launch(Dispatchers.IO) {
            customizeProfileSettings.isDarkThemeEnabled().collectLatest { isDark ->
                _state.update { it.copy(isDarkTheme = isDark) }
            }
        }*/
    }

    private fun getData() {
        getUserLoginState()
        getOrganizationName()
        getOrganizationImage()
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
            { getCurrentMemberUseCase() },
            ::onGetCurrentUserDataSuccess,
            ::onGetCurrentUserDataError
        )
    }

    private fun onGetCurrentUserDataSuccess(member: Member) {
        _state.update { it.copy(role = member.toOwnerUserUiState().role, isLoading = false) }
    }

    private fun onGetCurrentUserDataError(throwable: Throwable) {
        onError(throwable)
    }

    private fun getOrganizationImage() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageOrganizationDetails::getOrganizationImage,
            ::onGettingOrganizationImageSuccess,
            ::onError
        )
    }

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

    private fun getOrganizationName() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageOrganizationDetails::getOrganizationName,
            ::onGettingOrganizationNameSuccess,
            ::onError
        )
    }

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
        /*tryToExecute(
            manageChannels::getAllChannels,
            ::onGettingChannelsSuccess,
            ::onError
        )*/
    }

    private fun onGettingChannelsSuccess(channels: List<Channel>) {
        _state.update { it.copy(channels = channels.toUiState(), error = null, isLoading = false) }
    }

    private fun getUserLoginState() {
        viewModelScope.launch {
            getUserLoginStatus().let { isLoggedIn ->
                if (isLoggedIn) {
                    _state.update { it.copy(isLogged = true) }
                } else {
                    sendUiEffect(HomeUiEffect.NavigateToOrganizationName)
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