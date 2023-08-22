package com.chocolate.presentation.screens.on_boarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.onboardingRoute() {
    composable(Screen.OnBoarding.route) {
        OnboardingScreen()
    }
}

fun NavController.navigateToOnboarding() {
    navigate(Screen.OnBoarding.route)
}