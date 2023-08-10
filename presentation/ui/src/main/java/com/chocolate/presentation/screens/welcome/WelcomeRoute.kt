package com.chocolate.presentation.screens.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.welcomeRoute(navController: NavController) {
    composable(Screen.Welcome.route) {
        WelcomeScreen(navController)
    }
}

fun NavController.navigateToWelcome() {
    navigate(Screen.Welcome.route)
}