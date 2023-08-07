package com.chocolate.presentation.on_boarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.onboardingRoute(navController: NavController) {
    composable(Screen.OnBoarding.route) {
        OnboardingScreen(navController)
    }
}

fun NavController.navigateToOnboarding() {
    navigate(Screen.OnBoarding.route)
}