package com.chocolate.presentation.screens.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.welcomeRoute() {
    composable(Screen.Welcome.route) {
        WelcomeScreen()
    }
}

fun NavController.navigateToWelcome() {
    popBackStack()
    navigate(Screen.Welcome.route)
}