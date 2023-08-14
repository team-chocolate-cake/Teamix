package com.chocolate.viewmodel.login

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.organization.GetNameOrganizationsUseCase
import com.chocolate.usecases.user.LoginUseCase
import com.chocolate.usecases.user.SetUserLoginStateUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserLoginStateUseCase: SetUserLoginStateUseCase,
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()),LoginInteraction {

    init {
        getNameOrganization()
    }

    private fun getNameOrganization() {
        viewModelScope.launch {
            _state.update { it.copy(nameOrganization = getNameOrganizationsUseCase()) }
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
        tryToExecute({ loginUseCase(email, password) }, ::onSuccess, ::onError)
    }

    override fun onClickRetry() {
        login(_state.value.email, _state.value.password)
        sendUiEffect(LoginUiEffect.NavigateToForgetPassword)
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
        _state.update {
            it.copy(isLoading = false, error = "Invalid email or password")
        }
    }
}