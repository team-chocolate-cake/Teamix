package com.chocolate.viewmodel.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NetworkException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.usecases.user.AttemptUserLoginUseCase
import com.chocolate.usecases.user.SetUserLoginStateUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val attemptUserLoginUseCase: AttemptUserLoginUseCase,
    private val setUserLoginStateUseCase: SetUserLoginStateUseCase,
    private val stringsRes: StringsRes
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()),LoginInteraction {

    private val loginArgs: LoginArgs = LoginArgs(savedStateHandle)

    init {
        getNameOrganization()
    }

    private fun getNameOrganization() {
        viewModelScope.launch {
            _state.update { it.copy(nameOrganization = loginArgs.organizationName) }
        }
    }

    fun onClickForgetPassword(){
        sendUiEffect(LoginUiEffect.NavigateToForgetPassword)
    }

    override fun updateEmailState(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun updatePasswordState(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun login(email: String, password: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ attemptUserLoginUseCase(email, password) }, ::onSuccess, ::onError)
    }

    override fun onClickRetry() {
        login(_state.value.email, _state.value.password)
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
        val errorMessage = when(throwable){
            is NoConnectionException -> stringsRes.noConnectionMessage
            is NetworkException -> stringsRes.enterValidEmailAddress
            is ValidationException -> stringsRes.invalidEmailOrPassword
            is NullDataException -> stringsRes.organizationNameNotFound
            else -> stringsRes.globalMessageError
        }
        _state.update {
            it.copy(isLoading = false, error = errorMessage)
        }
    }
}