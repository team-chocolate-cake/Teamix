package com.chocolate.viewmodel.profile

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.EmptyEmailException
import com.chocolate.entities.exceptions.EmptyFullNameException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.SameUserDataException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.usecases.user.LogoutUseCase
import com.chocolate.usecases.user.UpdateUserInformationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val customizeProfileSettingsUseCase: CustomizeProfileSettingsUseCase,
    private val stringsRes: StringsRes
) : BaseViewModel<ProfileUiState, ProfileEffect>(ProfileUiState()), ProfileInteraction {

    init {
        getLastSelectedAppLanguage()
        getOwnUser()
    }

    private fun getLastSelectedAppLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            val language = customizeProfileSettingsUseCase.getLastSelectedAppLanguage()
            _state.update { it.copy(lastAppLanguage = language) }
        }
    }

    private fun getOwnUser() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ getCurrentUserDataUseCase() }, ::onGetOwnUserSuccess, ::onGetOwnUserError)
    }

    private fun onGetOwnUserSuccess(ownerUser: OwnerUser) {
        val ownerUserUi = ownerUser.toOwnerUserUiState()
        _state.update {
            it.copy(
                name = ownerUserUi.name,
                image = ownerUserUi.image,
                email = ownerUserUi.email,
                role = ownerUserUi.role,
                showNoInternetLottie = false,
                isLoading = false,
                error = null
            )
        }
    }

    private fun onGetOwnUserError(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException, is ValidationException ->
                sendUiEffect(ProfileEffect.NavigateToOrganizationScreen)
        }
        _state.update { it.copy(isLoading = false, showNoInternetLottie = true, error = null) }
    }

    override fun updateLanguageDialogState(showDialog: Boolean) {
        _state.update { it.copy(showLanguageDialog = showDialog, error = null) }
    }

    override fun updateThemeDialogState(showDialog: Boolean) {
        _state.update { it.copy(showThemeDialog = showDialog, error = null) }
    }

    override fun updateLogoutDialogState(showDialog: Boolean) {
        _state.update { it.copy(showLogoutDialog = showDialog, error = null) }
    }

    override fun updateWarningDialog(showDialog: Boolean) {
        _state.update { it.copy(showWarningDialog = showDialog, error = null) }
    }

    override fun onClickOwnerPower() {
        //not finished yet
       // sendUiEffect(ProfileEffect.fNavigateToOwnerPower)
    }

    override fun onUsernameChange(username: String) {
        if (_state.value.originalName.isEmpty()) {
            _state.update { it.copy(originalName = _state.value.name) }
        }
        _state.update { it.copy(name = username, error = null, newUsername = username) }
    }

    override fun onEmailChange(email: String) {
        if (_state.value.originalEmail.isEmpty()) {
            _state.update { it.copy(originalEmail = _state.value.email) }
        }

        _state.update { it.copy(email = email, error = null, newEmail = email) }
    }

    override fun onUserInformationFocusChange() {
        _state.update { it.copy(showWarningDialog = false, error = null) }
        val settingsState = Settings(fullName = _state.value.name, email = _state.value.email)
        tryToExecute(
            { updateUserInformationUseCase(settingsState) },
            ::onUpdateUserInformationSuccess,
            ::onError
        )
    }

    override fun onClickRetryToUpdatePersonalInformation() {
        onUserInformationFocusChange()
    }

    override fun onClickRetryToGetPersonalInformation() {
        getOwnUser()
    }

    override fun areUserDataEqual(): Boolean {
        val currentState = _state.value
        return currentState.name == currentState.newUsername || currentState.email == currentState.newEmail
    }

    override fun onRevertChange() {
        val currentState = _state.value
        val updatedState = currentState.copy(
            name = if (currentState.originalName == "") _state.value.name else currentState.originalName,
            email = if (currentState.originalEmail == "") _state.value.email else currentState.originalEmail,
            error = null
        )
        _state.update { updatedState }
        updateWarningDialog(false)
        _state.update { it.copy(pagerNumber = 2, error = null) }
    }

    override fun onClickProfileButton() {
        _state.update { it.copy(pagerNumber = 0, error = null, message = "") }
    }

    override fun onClickSettingsButton() {
        _state.update { it.copy(pagerNumber = 1, error = null, message = "") }
    }

    private fun onUpdateUserInformationSuccess(unit: Unit) {
        _state.update {
            it.copy(
                isLoading = false,
                newUsername = "",
                newEmail = "",
                error = null,
                message = stringsRes.successMessage
            )
        }
    }

    override fun updateClearHistoryState(showDialog: Boolean) {
        _state.update { it.copy(showClearHistoryDialog = showDialog, error = null) }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            EmptyEmailException -> stringsRes.emptyEmailMessage
            EmptyFullNameException -> stringsRes.emptyFullNameMessage
            SameUserDataException -> stringsRes.sameUserDataMessage
            is NoConnectionException -> stringsRes.noConnectionMessage
            else -> stringsRes.globalMessageError
        }
        _state.update { it.copy(isLoading = false, error = error) }
    }

    override fun onLogoutButtonClicked() {
        tryToExecute(
            call = { logoutUseCase() },
            onSuccess = ::onLogoutSuccess,
            onError = ::onLogoutFail
        )
    }

    private fun onLogoutSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, error = null) }
        sendUiEffect(ProfileEffect.NavigateToOrganizationScreen)
    }

    private fun onLogoutFail(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

    fun updateAppLanguage(newLanguage: String) {
        _state.value.lastAppLanguage = newLanguage
        tryToExecute(
            call = { customizeProfileSettingsUseCase.saveNewSelectedLanguage(newLanguage) },
            onSuccess = {_state.update { it.copy(error = null, isLoading = false) }},
            onError = ::onUpdateAppLanguageFail
        )
    }

    private fun onUpdateAppLanguageFail(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

}