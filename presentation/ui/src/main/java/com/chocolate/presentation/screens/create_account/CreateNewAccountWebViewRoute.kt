package com.chocolate.presentation.screens.create_account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createAccountWebViewRoute() {
    composable(Screen.CreateNewAccount.route) {
        CreateNewAccountWebViewScreen()
    }
}
fun NavController.navigateToCreateAccount(builder: NavOptionsBuilder.() -> Unit = {}){
    navigate(Screen.CreateNewAccount.route,builder)
}