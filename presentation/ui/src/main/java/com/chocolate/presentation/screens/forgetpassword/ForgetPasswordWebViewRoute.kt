package com.chocolate.presentation.screens.forgetpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.forgetPasswordWebViewRoute() {
    composable(Screen.ForgetPasswordWebView.route) {
        ForgetPasswordWebViewScreen()
    }
}
fun NavController.navigateToForgetPassword(builder: NavOptionsBuilder.() -> Unit = {}){
    navigate(Screen.ForgetPasswordWebView.route,builder)
}