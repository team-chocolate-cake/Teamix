package com.chocolate.presentation.on_boarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(Screen.OnBoarding.route) {
        OnBoardingScreen(navController)
    }
}

fun NavController.navigateToOnBoarding() {
    navigate(Screen.OnBoarding.route)
}