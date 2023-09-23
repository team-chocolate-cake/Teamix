package com.chocolate.presentation.screens.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.onboardingRoute() {
    composable(Screen.OnBoarding.route) {
        OnboardingScreen()
    }
}

fun NavController.navigateToOnboarding(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.OnBoarding.route,builder)
}