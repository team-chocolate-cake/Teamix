package com.chocolate.viewmodel.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.usecases.user.AttemptUserLoginUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.usecases.user.SetUserLoginStateUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val attemptUserLoginUseCase: AttemptUserLoginUseCase,
    private val setUserLoginStateUseCase: SetUserLoginStateUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteraction {

    private val loginArgs: LoginArgs = LoginArgs(savedStateHandle)

    init {
        getOrganizationName()
    }


    private fun getOrganizationName() {
        viewModelScope.launch {
            _state.update { it.copy(organizationName = loginArgs.organizationName) }
        }
    }

    override fun onClickForgetPassword() {
        sendUiEffect(LoginUiEffect.NavigateToForgetPassword)
    }

    override fun onChangeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onChangePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickSignIn(email: String, password: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ attemptUserLoginUseCase(email, password) }, ::onSuccess, ::onError)
    }

    override fun onClickRetry() {
        onClickSignIn(_state.value.email, _state.value.password)
    }

    override fun onClickPasswordVisibility(passwordVisibility: Boolean) {
        _state.update { it.copy(passwordVisibility = passwordVisibility) }
    }


    private fun onSuccess(isUserLogin: Boolean) {
        _state.update { it.copy(isLoading = false) }
        viewModelScope.launch {
            if (isUserLogin) {
                setUserLoginStateUseCase(true)
                sendUiEffect(LoginUiEffect.NavigationToHome)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is NetworkException -> stringsResource.enterValidEmailAddress
            is ValidationException -> stringsResource.invalidEmailOrPassword
            is NullDataException -> stringsResource.organizationNameNotFound
            else -> stringsResource.globalMessageError
        }
        _state.update {
            it.copy(isLoading = false, error = errorMessage)
        }
    }
}