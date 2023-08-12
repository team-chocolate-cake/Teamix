package com.chocolate.viewmodel.login

sealed interface LoginUiEffect{
    object NavigationToHome: LoginUiEffect
}