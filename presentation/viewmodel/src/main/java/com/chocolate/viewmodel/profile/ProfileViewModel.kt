package com.chocolate.viewmodel.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.uills.Empty
import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserRole
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.usecases.user.LogoutUseCase
import com.chocolate.usecases.user.UpdateUserInformationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserData: GetCurrentUserDataUseCase,
    private val updateUserInformation: UpdateUserInformationUseCase,
    private val logout: LogoutUseCase,
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<ProfileUiState, ProfileEffect>(ProfileUiState()), ProfileInteraction {
    init {
        getLastSelectedAppLanguage()
        getCurrentUser()
        isDarkTheme()
    }

    override fun updateEditUsernameDialogState(editUsernameState: Boolean) {
        _state.update { it.copy(showEditUsernameDialog = editUsernameState) }
    }

    override fun onUpdateLanguage(language: String) {
        _state.update { it.copy(lastAppLanguage = language, error = null, message = null) }
        tryToExecute(
            call = { customizeProfileSettings.saveNewSelectedLanguage(language) },
            onSuccess = {
                _state.update {
                    it.copy(
                        error = null,
                        isLoading = false,
                        message = null
                    )
                }
            },
            onError = ::onUpdateAppLanguageFail
        )
    }

    override fun onUpdateLanguageDialogState(showDialog: Boolean) {
        _state.update { it.copy(showLanguageDialog = showDialog, error = null, message = null) }
    }

    override fun onUpdateLogoutDialogState(showDialog: Boolean) {
        _state.update { it.copy(showLogoutDialog = showDialog, error = null, message = null) }
    }


    override fun onUsernameChange(username: String) {
        if (_state.value.originalName.isEmpty()) {
            _state.update { it.copy(originalName = _state.value.name) }
        }
        _state.update {
            it.copy(
                name = username,
                error = null,
                newUsername = username,
                message = null
            )
        }
    }

    override fun onUserInformationFocusChange() {
        val userInformationSettingsState = User(
            fullName = _state.value.name,
            email = _state.value.email,
            role = UserRole.fromValue(0),
            imageUrl = String.Empty,
            id = 0,
        )
        tryToExecute(
            { updateUserInformation(userInformationSettingsState) },
            ::onUpdateUserInformationSuccess,
            ::onError
        )
    }

    override fun onClickRetryToUpdatePersonalInformation() {
        onUserInformationFocusChange()
    }

    override fun onClickRetryToGetPersonalInformation() {
        getCurrentUser()
    }

    override fun areUserDataEqual(): Boolean {
        val currentState = _state.value
        return currentState.name == currentState.newUsername
    }

    override fun onClickProfileButton() {
        _state.update { it.copy(pagerNumber = 0, error = null, message = null) }
    }

    override fun onClickSettingsButton() {
        _state.update { it.copy(pagerNumber = 1, error = null, message = null) }
    }

    override fun onClickChangeMemberRole() {
        sendUiEffect(ProfileEffect.NavigateToSearchScreen)
    }

    override fun onClickDarkThemeSwitch(darkTheme: Boolean, context: Context) {
        _state.update { it.copy(isDarkTheme = !darkTheme) }
        viewModelScope.launch {
            customizeProfileSettings.updateDarkTheme(!darkTheme)
        }
    }

    override fun restartActivity(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivities(arrayOf(intent))
    }

    override fun onDismissEditTextDialog() {
        _state.update { it.copy(showEditUsernameDialog = false) }
    }


    override fun onLogoutButtonClicked() {
        tryToExecute(
            call = logout::invoke,
            onSuccess = ::onLogoutSuccess,
            onError = ::onLogoutFail
        )
    }

    private fun onUpdateUserInformationSuccess(unit: Unit) {
        _state.update {
            it.copy(
                isLoading = false,
                newUsername = String.Empty,
                error = null,
                message = stringsResource.successMessage
            )
        }
    }

    private fun onError(throwable: Throwable) {
        val validationMessage = when (throwable.message) {
            ErrorCode.THE_SAME_DATA.code -> stringsResource.theSameData
            ErrorCode.FAILED_EMAIL_WHEN_EMPTY.code -> stringsResource.failedEmailWhenEmpty
            ErrorCode.FAILED_FULL_NAME_WHEN_EMPTY.code -> stringsResource.failedFullNameWhenEmpty
            else -> stringsResource.globalMessageError
        }
        val error = when (throwable) {
            is ValidationException -> validationMessage
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(isLoading = false, error = error, message = null) }
    }

    private fun onLogoutSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, error = null, message = null) }
        sendUiEffect(ProfileEffect.NavigateToOrganizationScreen)
    }

    private fun onLogoutFail(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }


    private fun onUpdateAppLanguageFail(throwable: Throwable) {
        val messageError = when (throwable) {
            is NullDataException -> stringsResource.nullOrEmptyNewLanguage
            is TeamixException -> stringsResource.failedSaveSelectedLanguage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = messageError, isLoading = false) }
    }

    private fun getLastSelectedAppLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
           customizeProfileSettings.getLastSelectedAppLanguage().collectLatest {language ->
               _state.update { it.copy(lastAppLanguage = language) }
           }
        }
    }

    private fun isDarkTheme() {

        viewModelScope.launch(Dispatchers.IO) {
            customizeProfileSettings.isDarkThem().collectLatest { isDark ->
                _state.update { it.copy(isDarkTheme = isDark) }
            }
        }
    }

    private fun getCurrentUser() {
        tryToExecute(
            getCurrentUserData::invoke,
            ::onGetCurrentUserSuccess,
            ::onGetCurrentUserError
        )
    }

    private fun onGetCurrentUserSuccess(user: User) {
        val currentUserUi = user.toOwnerUserUiState()
        _state.update {
            it.copy(
                name = currentUserUi.name,
                imageUrl = currentUserUi.imageUrl,
                email = currentUserUi.email,
                role = currentUserUi.role,
                showNoInternetLottie = false,
                isLoading = false,
                error = null,
                message = null
            )
        }
    }

    private fun onGetCurrentUserError(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException, is ValidationException ->
                sendUiEffect(ProfileEffect.NavigateToOrganizationScreen)
        }
        _state.update {
            it.copy(
                isLoading = false,
                showNoInternetLottie = true,
                error = null,
                message = null
            )
        }
    }
}