package com.chocolate.viewmodel.profile

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings
import com.chocolate.usecases.user.GetOwnUserUseCase
import com.chocolate.usecases.user.UpdateUsernameUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getOwnUserUseCase: GetOwnUserUseCase,
    private val updateUsernameUseCase: UpdateUsernameUseCase
):BaseViewModel<ProfileUiState,ProfileEffect>(ProfileUiState()), ProfileInteraction {

    init {
        getOwnUser()
    }
    private fun getOwnUser() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ getOwnUserUseCase() }, ::onGetOwnUserSuccess, ::onError)
    }

    private fun onGetOwnUserSuccess(ownerUser: OwnerUser) {
        val ownerUserUi = ownerUser.toOwnerUserUiState()
        _state.update {
            it.copy(
                name = ownerUserUi.name,
                image = ownerUserUi.image,
                email = ownerUser.email,
                isLoading = false,
                error = null
            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

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

    override fun onUsernameChange(username: String) {
        _state.update { it.copy(name = username) }

    }

    override fun onEmailChange(email: String) {
        _state.update { it.copy(name = email) }
    }

    override fun onUsernameFocusChange() {
        val settingsState = Settings(fullName = _state.value.name)
        tryToExecute({updateUsernameUseCase(settingsState)},::onUpdateUsernameSuccess,::onError)
    }

    override fun onEmailFocusChange() {
        val settingsState = Settings(fullName = _state.value.email)
        tryToExecute({updateUsernameUseCase(settingsState)},::onUpdateEmailSuccess,::onError)
    }

    private fun onUpdateEmailSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, error = null, message = "success") }
    }

    override fun onClickRetry() {
        onUsernameFocusChange()
    }

    private fun onUpdateUsernameSuccess(unit: Unit) {
            _state.update { it.copy(isLoading = false, error = null, message = "success") }
    }

    override fun updateClearHistoryState(showDialog: Boolean) {
        _state.update { ProfileUiState(showClearHistoryDialog = showDialog) }
    }

}