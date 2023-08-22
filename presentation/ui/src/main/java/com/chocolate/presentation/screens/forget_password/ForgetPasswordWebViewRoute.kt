package com.chocolate.presentation.screens.forget_password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.forgetPasswordWebViewRoute() {
    composable(Screen.ForgetPasswordWebView.route) {
        ForgetPasswordWebViewScreen()
    }
}
fun NavController.navigateToForgetPassword(){
    navigate(Screen.ForgetPasswordWebView.route)
}