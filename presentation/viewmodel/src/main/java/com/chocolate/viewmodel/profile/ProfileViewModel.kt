package com.chocolate.viewmodel.profile

import com.chocolate.entities.exceptions.EmptyEmailException
import com.chocolate.entities.exceptions.EmptyFullNameException
import com.chocolate.entities.exceptions.SameUserDataException
import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.usecases.user.UpdateUserInformationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase
):BaseViewModel<ProfileUiState,ProfileEffect>(ProfileUiState()), ProfileInteraction {

    init {
        getOwnUser()
    }
    private fun getOwnUser() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ getCurrentUserDataUseCase() }, ::onGetOwnUserSuccess, ::onError)
    }

    private fun onGetOwnUserSuccess(ownerUser: OwnerUser) {
        val ownerUserUi = ownerUser.toOwnerUserUiState()
        _state.update {
            it.copy(
                name = ownerUserUi.name,
                image = ownerUserUi.image,
                email = ownerUserUi.email,
                role = ownerUserUi.role,
                isLoading = false,
                error = null
            )
        }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            EmptyEmailException -> "Email can't be empty"
            EmptyFullNameException -> "Full name can't be empty"
            SameUserDataException -> "User Information can't be the same"
            else -> throwable.localizedMessage
        }
        _state.update { it.copy(isLoading = false, error = error) }
    }

    override fun updateLanguageDialogState(showDialog: Boolean) {
        _state.update {it.copy(showLanguageDialog = showDialog) }
    }

    override fun updateThemeDialogState(showDialog: Boolean) {
        _state.update { it.copy(showThemeDialog = showDialog) }
    }

    override fun updateLogoutDialogState(showDialog: Boolean) {
        _state.update { it.copy(showLogoutDialog = showDialog) }
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
        val settingsState = Settings(fullName = _state.value.name, email = _state.value.email)
        tryToExecute(
            { updateUserInformationUseCase(settingsState) },
            ::onUpdateUsernameSuccess,
            ::onError
        )
    }

    override fun onEmailFocusChange() {
        val settingsState = Settings(fullName = _state.value.name, email = _state.value.email)
        tryToExecute(
            { updateUserInformationUseCase(settingsState) },
            ::onUpdateEmailSuccess,
            ::onError
        )
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
        _state.update { it.copy(showClearHistoryDialog = showDialog) }
    }

}