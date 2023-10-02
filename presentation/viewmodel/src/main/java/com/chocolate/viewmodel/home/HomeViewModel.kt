package com.chocolate.viewmodel.home

import com.chocolate.entities.entity.Channel
import com.chocolate.entities.entity.Member
import com.chocolate.entities.util.EmptyMemberIdException
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.channel.ManageChannelUseCase
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.member.IsMemberLoggedInUseCase
import com.chocolate.usecases.organization.ManageOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.profile.toOwnerUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val manageChannelUseCase: ManageChannelUseCase,
    private val manageOrganizationUseCase: ManageOrganizationUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val isMemberLoggedInUseCase: IsMemberLoggedInUseCase,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    init {
        getData()
        isDarkTheme()
    }

    private fun isDarkTheme() {
        collectFlow(customizeProfileSettings.isDarkThemeEnabled()) {
            this.copy(isDarkTheme = it)
        }
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

    override fun onClickSavedLater() {
        sendUiEffect(HomeUiEffect.NavigationToSavedLater)
    }

    override fun onClickChannel(id: String, name: String) {
        sendUiEffect(HomeUiEffect.NavigateToChannel(id.toInt(), name))
    }

    override fun onClickTopic(channelId: String, topicId: String, name: String) {
        sendUiEffect(HomeUiEffect.NavigateToTopic(channelId.toInt(), name, topicId))
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
            manageOrganizationUseCase::getOrganizationImage,
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
            manageOrganizationUseCase::getOrganizationName,
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
        tryToExecuteFlow(
            { manageChannelUseCase.getChannelsForCurrentMember() },
            ::onGettingChannelsSuccess,
            ::onError
        )
    }

    private suspend fun onGettingChannelsSuccess(channels: Flow<List<Channel>>) {
        channels.collect { channel ->
            _state.update {
                it.copy(
                    channels = channel.toUiState(),
                    error = null,
                    isLoading = false
                )
            }
        }
    }

    private fun getUserLoginState() {
        tryToExecuteFlow({ isMemberLoggedInUseCase() }, ::onGettingMemberLoginState, ::onError)
    }

    private suspend fun onGettingMemberLoginState(isLoggedIn: Flow<Boolean>) {
        isLoggedIn.collectLatest {result->
            if (result) {
                _state.update { it.copy(isLogged = true, error = null, isLoading = false) }
            } else {
                sendUiEffect(HomeUiEffect.NavigateToOrganizationName)
            }
        }

    }

    private fun onError(throwable: Throwable) {
        when (throwable) {
            is EmptyOrganizationNameException, is EmptyMemberIdException -> {
                sendUiEffect(
                    HomeUiEffect.NavigateToOrganizationName
                )
            }

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