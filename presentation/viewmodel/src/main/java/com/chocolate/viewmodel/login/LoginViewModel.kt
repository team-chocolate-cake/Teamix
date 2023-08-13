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
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()) {

    init {
        getNameOrganization()
    }

    private fun getNameOrganization() {
        viewModelScope.launch {
            _state.update { it.copy(nameOrganization = getNameOrganizationsUseCase()) }
        }
    }

    fun updateEmailState(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun updatePasswordState(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun login(email: String, password: String) {
        tryToExecute({ loginUseCase(email, password) }, ::onSuccess, ::onError)
    }

    private fun onSuccess(isUserLogin: Boolean) {
        viewModelScope.launch {
            if (isUserLogin) {
                setUserLoginStateUseCase(true)
                sendUiEffect(LoginUiEffect.NavigationToHome)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update {
            it.copy()
        }
    }
}