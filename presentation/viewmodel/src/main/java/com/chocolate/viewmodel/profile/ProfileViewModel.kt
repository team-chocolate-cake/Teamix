package com.chocolate.viewmodel.profile

import com.chocolate.usecases.user.LogoutUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel<ProfileUiState, ProfileEffect>(ProfileUiState()), ProfileInteraction {

    override fun updateLanguageDialogState(showDialog: Boolean) {
        _state.update { ProfileUiState(showLanguageDialog = showDialog) }
    }

    override fun updateThemeDialogState(showDialog: Boolean) {
        _state.update { ProfileUiState(showThemeDialog = showDialog) }
    }

    override fun updateLogoutDialogState(showDialog: Boolean) {
        _state.update { ProfileUiState(showLogoutDialog = showDialog) }
    }

    override fun onClickOwnerPower() {
        sendUiEffect(ProfileEffect.NavigateToOwnerPower)
    }

    override fun onLogoutButtonClicked() {
        tryToExecute(
            call = { logoutUseCase() },
            onSuccess = ::onLogoutSuccess,
            onError = ::onLogoutFail
        )
    }

    private fun onLogoutSuccess(unit: Unit) = sendUiEffect(ProfileEffect.NavigateToLoginScreen)

    private fun onLogoutFail(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

    override fun updateClearHistoryState(showDialog: Boolean) {
        _state.update { ProfileUiState(showClearHistoryDialog = showDialog) }
    }

}