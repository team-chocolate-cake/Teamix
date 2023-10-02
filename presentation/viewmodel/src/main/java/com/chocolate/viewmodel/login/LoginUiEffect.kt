package com.chocolate.viewmodel.login

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface LoginUiEffect : BaseViewModel.BaseUiEffect{
    object NavigationToHome: LoginUiEffect
    data class NavigateToCreateNewAccount(val role: String): LoginUiEffect
}