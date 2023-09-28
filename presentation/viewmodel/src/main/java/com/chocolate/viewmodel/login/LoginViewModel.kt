package com.chocolate.viewmodel.login

import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.util.EmptyEmailException
import com.chocolate.entities.util.EmptyPasswordException
import com.chocolate.entities.util.NetworkException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.entities.util.NullDataException
import com.chocolate.entities.util.ValidationException
import com.chocolate.entities.util.WrongEmailException
import com.chocolate.entities.util.WrongEmailOrPasswordException
import com.chocolate.usecases.member.AttemptMemberLoginUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val attemptUserLogin: AttemptMemberLoginUseCase,
    private val stringsResource: StringsResource,
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteraction {
    private val loginArgs: LoginArgs = LoginArgs(savedStateHandle)
    init { getOrganizationName() }

    override fun onClickCreateNewAccount() {
        sendUiEffect(LoginUiEffect.NavigateToCreateNewAccount("Member"))
    }

    override fun onChangeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onChangePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickSignIn(email: String, password: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ attemptUserLogin(email, password) }, ::onLoginSuccess, ::onError)
    }

    override fun onClickRetry() {
        onClickSignIn(_state.value.email, _state.value.password)
    }

    override fun onSnackBarDismissed() {
        _state.update { it.copy(error = null) }
    }

    override fun onClickPasswordVisibility(passwordVisibility: Boolean) {
        _state.update { it.copy(passwordVisibility = passwordVisibility, error = null) }
    }

    private fun getOrganizationName() {
        _state.update { it.copy(organizationName = loginArgs.organizationName) }
    }

    private fun onLoginSuccess(isUserLogin: Boolean) {
        _state.update { it.copy(isLoading = false, isLogged = isUserLogin) }
        sendUiEffect(LoginUiEffect.NavigationToHome)
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is NetworkException -> stringsResource.enterValidEmailAddress
            is ValidationException -> stringsResource.invalidEmailOrPassword
            is NullDataException -> stringsResource.organizationNameNotFound
            is EmptyEmailException -> stringsResource.emptyEmailMessage
            is EmptyPasswordException -> stringsResource.emptyPassword
            is WrongEmailException -> stringsResource.invalidEmailOrPassword
            is WrongEmailOrPasswordException -> stringsResource.invalidEmailOrPassword
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }
}