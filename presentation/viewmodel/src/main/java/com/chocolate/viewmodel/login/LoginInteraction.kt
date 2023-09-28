package com.chocolate.viewmodel.login

interface LoginInteraction {
    fun onChangeEmail(email: String)
    fun onChangePassword(password: String)
    fun onClickSignIn(email: String, password: String)
    fun onClickRetry()
    fun onSnackBarDismissed()
    fun onClickPasswordVisibility(passwordVisibility:Boolean)
    fun onClickCreateNewAccount()
}