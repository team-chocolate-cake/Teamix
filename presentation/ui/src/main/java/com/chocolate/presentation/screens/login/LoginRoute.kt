package com.chocolate.presentation.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.organiztion.OrganizationScreen

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable(Screen.Login.route) {
        LoginScreen(navController = navController)
    }
}

fun NavController.navigateToLogin() {
    navigate(Screen.Login.route)
}
