package com.chocolate.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chocolate.presentation.welcome.WelcomeScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
    }
}