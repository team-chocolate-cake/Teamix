package com.chocolate.presentation.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.welcomeRoute(navController: NavController) {
    composable(Screen.Welcome.route) {
        WelcomeScreen(navController)
    }
}